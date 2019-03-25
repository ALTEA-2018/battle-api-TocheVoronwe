package com.miage.altea.tp.battle_api.repository;

import com.miage.altea.tp.battle_api.bo.Battle;

import java.util.List;

public interface BattleRepository {
    public List<Battle> findBattles();
    public Battle findBattle(String uuid);
    public Battle createBattle(Battle battle);
    public Battle updateBattle(Battle battle);
}
