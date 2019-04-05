package com.miage.altea.tp.battle_api.service;

import com.miage.altea.tp.battle_api.bo.Battle;

import java.util.List;

public interface BattleService {
    public Battle createBattle(String trainer, String opponent);
    public List<Battle> getBattles();
    public Battle getBattle(String uuid);
    public Battle attack(String uuid, String trainer);
}
