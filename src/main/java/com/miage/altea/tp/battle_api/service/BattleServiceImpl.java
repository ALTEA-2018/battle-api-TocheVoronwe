package com.miage.altea.tp.battle_api.service;

import com.miage.altea.tp.battle_api.bo.Battle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BattleServiceImpl implements BattleService {
    @Value("${trainer-api.url}")
    String trainerApiUrl;

    public Battle createBattle(String trainerName, String opponentName) {

        UUID uuid = UUID.randomUUID();


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

    public int calculateDamages(int attack, int level, int defense) {
        var res = (2 * level) / 5;
        var diff = (2 * attack) / defense;

        res += diff + 2;
        return res;
    }
}
