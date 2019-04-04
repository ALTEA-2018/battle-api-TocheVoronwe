package com.miage.altea.tp.battle_api.service;

import com.miage.altea.tp.battle_api.bo.*;
import com.miage.altea.tp.battle_api.repository.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BattleServiceImpl implements BattleService {
    @Value("${trainer-api.url}")
    private String trainerApiUrl;
    private RestTemplate restTemplate;
    private PokemonTypeService pokemonTypeService;
    private BattleRepository battleRepository;
    StatsCalculator statsCalculator;

    public Battle createBattle(String trainerName, String opponentName) {
        Battle battle = new Battle();
        UUID uuid = UUID.randomUUID();

        Trainer trainer = restTemplate.getForObject(trainerApiUrl + "/trainers/" + trainerName,
                Trainer.class);
        System.out.println(trainer);
        Trainer opponent = restTemplate.getForObject(trainerApiUrl + "/trainers/" + opponentName,
                Trainer.class);

        BattleTrainer battleTrainer = setBattleTrainer(trainer);
        BattleTrainer battleOpponent = setBattleTrainer(opponent);
        if (trainerStart(battleTrainer, battleOpponent))
            battleTrainer.setNextTurn(true);
        else
            battleOpponent.setNextTurn(false);
        battle.setTrainer(battleTrainer);
        battle.setOpponent(battleOpponent);
        battle.setUuid(uuid);
        return battleRepository.createBattle(battle);
    }

    public List<Battle> getBattles() {
        return null;
    }

    public Battle getBattle(String uuid) {
        return battleRepository.findBattle(uuid);
    }

    public Battle attack(String uuid, String trainer, String attack) {

        Battle battle = battleRepository.findBattle(uuid);

        BattleTrainer battleTrainer = battle.getTrainer().getName().equals(trainer)
                ? battle.getTrainer()
                : battle.getOpponent();
        if (!battleTrainer.getName().equals(trainer)
                || !battleTrainer.isNextTurn())
            return null;
        BattleTrainer opponent = !battle.getTrainer().getName().equals(trainer)
                ? battle.getTrainer()
                : battle.getOpponent();

        BattlePokemon trainerPokemon = getActivePokemon(battleTrainer.getTeam());
        BattlePokemon opponentPokemon = getActivePokemon(opponent.getTeam());

        return battleRepository.findBattle(uuid);
    }

    public int calculateDamages(float attack, float level, float defense) {
        var res = (2 * level) / 5;
        var diff = (2 * attack) / defense;

        res += diff + 2;
        return Math.round(res);
    }

    private BattlePokemon getActivePokemon(List<BattlePokemon>  battlePokemons) {
        return battlePokemons.stream().filter(p -> p.isAlive()).findFirst().orElse(null);
    }

    private PokemonType getPokemonType(int id) {
        return pokemonTypeService.getPokemonById(id);
    }

    private BattlePokemon getBattlePokemon(PokemonType pokemonType) {
        BattlePokemon battlePokemon = new BattlePokemon();

        battlePokemon.setType(pokemonType);
        return battlePokemon;
    }

    private BattleTrainer setBattleTrainer(Trainer trainer) {
        BattleTrainer battleTrainer = new BattleTrainer();
        List<BattlePokemon> team = new ArrayList<>();
        battleTrainer.setName(trainer.getName());
        battleTrainer.setTeam(setTeamStat(trainer.getTeam()));
        return battleTrainer;
    }

    private boolean trainerStart(BattleTrainer trainer, BattleTrainer opponent) {
        var trainerPokemon = trainer.getTeam().get(0);
        var opponentPokemon = opponent.getTeam().get(0);
        return (trainerPokemon.getSpeed() > opponentPokemon.getSpeed());
    }

    private List<BattlePokemon> setTeamStat(List<TeamMember> teamMembers) {
        List<BattlePokemon> battlePokemons = new ArrayList<>();
        teamMembers.parallelStream().forEach(m -> {
            BattlePokemon battlePokemon = new BattlePokemon();
            var pokemon = this.pokemonTypeService.getPokemonById(m.getPokemonType());
            var level = m.getLevel();
            if (pokemon != null) {
                battlePokemon.setId(pokemon.getId());
                battlePokemon.setHp(statsCalculator.calculateHp(pokemon.getStats().getHp(), level));
                battlePokemon.setMaxHp(battlePokemon.getHp());
                battlePokemon.setAttack(statsCalculator.calculateStat(pokemon.getStats().getAttack(), level));
                battlePokemon.setDefense(statsCalculator.calculateStat(pokemon.getStats().getDefense(), level));
                battlePokemon.setSpeed(statsCalculator.calculateStat(pokemon.getStats().getSpeed(), level));
                battlePokemon.setLevel(level);
                battlePokemon.setKo(false);
                battlePokemon.setAlive(true);
                battlePokemon.setType(pokemon);
                battlePokemons.add(battlePokemon);
            }
        });
        return battlePokemons;
    }

    @Autowired()
    @Qualifier("trainerApiRestTemplate")
    void setTrainerApiRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @Autowired
    void setStatsCalculator(StatsCalculator statsCalculator) {
        this.statsCalculator = statsCalculator;
    }

    @Autowired
    void setBattleRepository(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }
}
