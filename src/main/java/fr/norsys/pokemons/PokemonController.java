package fr.norsys.pokemons;

import fr.norsys.models.Hunter;
import fr.norsys.hunters.HunterRepository;
import fr.norsys.models.Move;
import fr.norsys.models.Pokemon;
import fr.norsys.models.PokemonInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.HeaderParam;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
@Api(value = "/api/pokemons", description = "Operations about pokemons")
public class PokemonController {

    @Autowired
    PokemonSparkRepository pokemonSparkRepository;


    @GetMapping("/api/pokemons")
    @ApiOperation(value = "Finds all pokemons",
            response = PokemonInfo.class,
            responseContainer = "List",
            authorizations = @Authorization(value = "Authorization"))
    public @ResponseBody List<PokemonInfo> findAllPokemons() {
        return pokemonSparkRepository.findAllPokemons();
    }


}
