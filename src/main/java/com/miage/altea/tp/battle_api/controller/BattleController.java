package com.miage.altea.tp.battle_api.controller;

import com.miage.altea.tp.battle_api.bo.Battle;
import com.miage.altea.tp.battle_api.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/battles")
@RestController()
public class BattleController {
    @Autowired
    BattleService battleService;
    @PostMapping()
    public Battle createBattle(@RequestParam("trainer") String trainerName,
                               @RequestParam("opponent") String opponentName) {
        return battleService.createBattle(trainerName, opponentName);
    }

    @GetMapping("/{battleUuid}")
    public Battle getBattle(@PathVariable("battleUuid") String uuid) {
        System.out.println(uuid);
        return battleService.getBattle(uuid);
    }
}
