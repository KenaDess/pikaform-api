package fr.norsys.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pokemon {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private Integer pokemonId;
    private Integer level=0;

    @JsonIgnore
    @ManyToOne
    private Hunter hunter;

}
