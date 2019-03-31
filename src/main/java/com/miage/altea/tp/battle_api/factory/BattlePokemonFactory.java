package com.miage.altea.tp.battle_api.factory;

import com.miage.altea.tp.battle_api.bo.BattlePokemon;
import com.miage.altea.tp.battle_api.bo.PokemonType;

public interface BattlePokemonFactory {
    public BattlePokemon createBattlePokemon(PokemonType pokemonType, int level);
}
