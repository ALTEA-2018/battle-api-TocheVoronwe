package com.miage.altea.tp.battle_api.bo;

import lombok.Data;

@Data
public class BattlePokemon {
    int id;
    int hp;
    int maxHp;
    int attack;
    int defense;
    int speed;
    int level;
    boolean ko;
    boolean alive;
    PokemonType type;
}
