package fr.norsys.models;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonInfo {
    private String ndex;
    private String species;
    private String forme;
    private String type1;
    private String type2;
    private String ability1;
    private String ability2;
    private String abilityH;
    private String hp;
    private String attack;
    private String defense;
    private String spattack;
    private String spdefense;
    private String speed;
    private String total;
    private String weight;
    private String height;
    private String percentmale;
    private String percentfemale;
    private String preevolution;
    private String egggroup1;
    private String egggroup2;
}
