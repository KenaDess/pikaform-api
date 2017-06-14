package fr.norsys.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Battle {
    private Hunter hunterAttacker;
    private Hunter hunterDefender;

    private Pokemon attacker;
    private PokemonInfo attackerInfo;
    private Float attackerHP;

    private Pokemon defender;
    private PokemonInfo defenderInfo;
    private Float defenderHP;

    private boolean init;
    private Boolean win;

    private Move moveToGo;
}
