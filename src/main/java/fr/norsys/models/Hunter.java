package fr.norsys.models;


import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.norsys.models.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hunter {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;

    @NotNull(message = "password should not be null")
    @NotEmpty(message = "password should not be empty")
    private String password;

    @NotNull(message = "role should not be null")
    @NotEmpty(message = "role should not be empty")
    private String role;

    private String avatar;

    @OneToMany(targetEntity=Pokemon.class,mappedBy = "hunter",fetch =FetchType.EAGER)
    private List<Pokemon> pokemons;

    private boolean catchFirstPokemon =false;
}