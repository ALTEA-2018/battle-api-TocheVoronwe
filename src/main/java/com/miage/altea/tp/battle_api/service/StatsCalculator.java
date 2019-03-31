package com.miage.altea.tp.battle_api.service;

public interface StatsCalculator {
    public int calculateHp(int base, int level);
    public int calculateStat(int base, int level);
}
