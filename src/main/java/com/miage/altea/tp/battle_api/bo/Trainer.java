package com.miage.altea.tp.battle_api.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Trainer {
    String name;
    List<TeamMember> team;
}
