package com.miage.altea.tp.battle_api.repository;

import com.miage.altea.tp.battle_api.bo.Battle;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository()
public class BattleRepositoryImpl implements BattleRepository {
    private Map<UUID, Battle> battles = new HashMap<>();

    public List<Battle> findBattles() {
        return new ArrayList<>(battles.values());
    }

    public Battle findBattle(String uuid) {
        return battles.getOrDefault(uuid, null);
    }

    public Battle createBattle(Battle battle) {
        UUID uuid = battle.getUuid();
        battles.put(uuid, battle);
        return battles.get(uuid);
    }

    public Battle updateBattle(Battle battle) {
        UUID uuid = battle.getUuid();
        if (battles.containsKey(uuid))
            battles.replace(uuid, battle);
        return battles.get(uuid);
    }

}
