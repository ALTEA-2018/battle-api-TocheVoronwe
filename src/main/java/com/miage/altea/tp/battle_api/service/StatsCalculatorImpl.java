package com.miage.altea.tp.battle_api.service;

import org.springframework.stereotype.Service;

@Service()
public class StatsCalculatorImpl implements StatsCalculator {
    public int round(final float nb) {
        float res = nb;
        float t = res % 1;
        res -= t;
        return Math.round(res);
    }

    public int calculateHp(float base, float level) {
        float res = level / 50;
        res *= base;
        res += 10 + level;
        return round(res);
    }

    public int calculateStat(float base, float level) {
        float res = level / 50;
        res *= base;
        res += 5;
        return round(res);
    }
}
