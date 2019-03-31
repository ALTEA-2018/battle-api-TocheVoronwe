package com.miage.altea.tp.battle_api.service;

public class StatsCalculatorImpl implements StatsCalculator{
    public int calculateHp(int base, int level) {
        var res = level / 50;
        res *= base;
        res += 5;
        return res;
    }

    public int calculateStat(int base, int level) {
        var res = level / 50;
        res *= base;
        res += level + 10;
        return res;
    }
}
