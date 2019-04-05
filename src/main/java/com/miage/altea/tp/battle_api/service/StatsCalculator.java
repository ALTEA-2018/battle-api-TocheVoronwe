package com.miage.altea.tp.battle_api.service;

public interface StatsCalculator {
    int round(final float nb);
    int calculateHp(float base, float level);
    int calculateStat(float base, float level);
}
