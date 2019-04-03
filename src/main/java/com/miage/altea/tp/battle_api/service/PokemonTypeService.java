package com.miage.altea.tp.battle_api.service;


import com.miage.altea.tp.battle_api.bo.PokemonType;
import org.springframework.web.client.RestTemplate;

public interface PokemonTypeService {
    PokemonType getPokemonById(int id);
    void setRestTemplate(RestTemplate restTemplate);
}