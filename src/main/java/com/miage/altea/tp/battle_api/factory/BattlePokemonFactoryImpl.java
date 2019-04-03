package com.miage.altea.tp.battle_api.factory;

import com.miage.altea.tp.battle_api.bo.BattlePokemon;
import com.miage.altea.tp.battle_api.bo.PokemonType;
import org.springframework.stereotype.Service;

@Service
public class BattlePokemonFactoryImpl implements BattlePokemonFactory {
    public BattlePokemon createBattlePokemon(PokemonType pokemonType, int level) {
        BattlePokemon battlePokemon = new BattlePokemon();
        battlePokemon.setType(pokemonType);
        return battlePokemon;
    }
}
