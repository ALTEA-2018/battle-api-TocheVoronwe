package com.miage.altea.tp.battle_api.controller;

import com.miage.altea.tp.battle_api.bo.Battle;
import com.miage.altea.tp.battle_api.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return battleService.getBattle(uuid);
    }


    @PostMapping("/{battleUuid}/{name}/attack")
    public ResponseEntity<Object> attack(@PathVariable("battleUuid") String uuid,
                                        @PathVariable("name") String name) {
        Battle battle = battleService.attack(uuid, name);
        if (battle == null)
            return ResponseEntity.badRequest().body("Not your turn!");
        return ResponseEntity.status(HttpStatus.OK).body(battle);
    }
}
