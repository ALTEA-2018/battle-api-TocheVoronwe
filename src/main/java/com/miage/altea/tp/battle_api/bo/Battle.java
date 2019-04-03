package com.miage.altea.tp.battle_api.bo;

import lombok.Data;

import java.util.UUID;

@Data
public class Battle {
    UUID uuid;
    BattleTrainer trainer;
    BattleTrainer opponent;
}
