package com.miage.altea.tp.battle_api.bo;

import lombok.Data;

import java.util.List;

@Data
public class BattleTrainer {
    String name;
    boolean nextTurn;
    List<BattlePokemon> team;

    public void updatePokemon(BattlePokemon pokemon) {
        team.stream().map(t -> t.getId() == pokemon.getId() ? pokemon : t);
    }
}
