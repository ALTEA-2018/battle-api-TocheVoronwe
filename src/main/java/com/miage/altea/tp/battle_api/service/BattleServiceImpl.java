package com.miage.altea.tp.battle_api.service;

import com.miage.altea.tp.battle_api.bo.Battle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleServiceImpl implements BattleService {
    @Value("${trainer-api.url}")
    String trainerApiUrl;

    public Battle createBattle(String trainer, String opponent) {

        return null;
    }

    public List<Battle> getBattles() {
        return null;
    }

    public Battle getBattle(String uuid) {
        return null;
    }

    public Battle attack(String uuid, String trainer, String attack) {
        return null;
    }
}