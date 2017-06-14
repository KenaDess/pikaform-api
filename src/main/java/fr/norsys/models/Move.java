package fr.norsys.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Move {
    private String id;
    private String move;
    private String description;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
    private String priority;
    private String crit;

}
