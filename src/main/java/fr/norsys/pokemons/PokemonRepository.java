package fr.norsys.pokemons;

import fr.norsys.models.Pokemon;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<Pokemon, Long> {

}