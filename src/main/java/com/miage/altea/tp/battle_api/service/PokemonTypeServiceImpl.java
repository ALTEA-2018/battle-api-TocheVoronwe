package com.miage.altea.tp.battle_api.service;

import com.miage.altea.tp.battle_api.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {
    private String url;

    private RestTemplate restTemplate = new RestTemplate();

    @CachePut("pokemons-type")
    public PokemonType getPokemonById(int id) {
        return restTemplate.getForObject(url + "/pokemon-types/" + id, PokemonType.class);
    }

    @Autowired()
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemon-type-api.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.url = pokemonServiceUrl;
    }
}