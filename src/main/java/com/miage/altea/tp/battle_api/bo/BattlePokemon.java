package com.miage.altea.tp.battle_api.bo;

import lombok.Data;

@Data
public class BattlePokemon {
    int hp;
    int maxHP;
    PokemonType type;
}
