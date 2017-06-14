package fr.norsys.hunters;

import com.google.common.collect.Lists;
import fr.norsys.models.Hunter;
import fr.norsys.models.Pokemon;
import fr.norsys.models.PokemonInfo;
import fr.norsys.pokemons.PokemonRepository;
import fr.norsys.pokemons.PokemonSparkRepository;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@Slf4j
@Api(value = "/api/hunters", description = "Operations about hunters")
public class HunterController {

    @Autowired
    HunterRepository hunterRepository;

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    PokemonSparkRepository pokemonSparkRepository;

    @GetMapping("/api/hunters")
    @RolesAllowed("ADMIN")
    @ApiOperation(value = "Finds all Hunters",
            response = Hunter.class,
            responseContainer = "List",
            authorizations = @Authorization(value = "Authorization"))
    public @ResponseBody List<Hunter> getHunters() {
        return hunterRepository.findAll();
    }

    @ApiOperation(value = "Register new hunter")
    @CrossOrigin(origins = "http://127.0.0.1:8081", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/api/hunters/register")
    public @ResponseBody void registerHunter(@ApiParam(value = "Hunter to register", required = true) @RequestBody Hunter hunter) throws Exception {
        if(hunter.getAvatar() == null || hunter.getAvatar().isEmpty()){
            throw new Exception("avatar is null or empty");
        }
        hunter.setRole("HUNTER");
        try {
            hunterRepository.save(hunter);
        }catch(DataIntegrityViolationException constraint){
                log.debug("user is created",constraint);
                throw new Exception("user is created");
        }
    }

    @GetMapping("/api/hunters/{hunterId}/pokemons")
    @ApiOperation(value = "Finds all pokemons for hunter",
            response = PokemonInfo.class,
            responseContainer = "List",
            authorizations = @Authorization(value = "Authorization"))
    public @ResponseBody List<PokemonInfo> getHunters(@ApiParam(value = "Id of hunter", required = true) @PathVariable String hunterId) {
        Hunter hunter = hunterRepository.findOne(Long.valueOf(hunterId));
        List<Pokemon> pokemons = hunter.getPokemons();
        return pokemonSparkRepository.findAllPokemonsById(pokemons.stream().flatMap(pokemon -> Stream.of(pokemon.getPokemonId())).distinct().collect(Collectors.toList()));
    }

    @GetMapping("/api/hunters/{hunterId}/catchPokemon")
    @ApiOperation(value = "Cacth first pokemon for your hunter",
            response = PokemonInfo.class,
            responseContainer = "List",
            authorizations = @Authorization(value = "Authorization"))
    public @ResponseBody PokemonInfo catchPokemon(@ApiParam(value = "Id of hunter", required = true) @PathVariable String hunterId) throws Exception {
        Hunter hunter = hunterRepository.findOne(Long.valueOf(hunterId));
        if(!hunter.isCatchFirstPokemon()){
            PokemonInfo pokemonInfo = pokemonSparkRepository.findAllPokemonsById(Lists.newArrayList(new Integer(new Random().nextInt(720)))).get(0);
            Pokemon pokemon = Pokemon.builder().pokemonId(Integer.valueOf(pokemonInfo.getNdex())).hunter(hunter).level(0).build();
            if(hunter.getPokemons()==null){
                hunter.setPokemons(new ArrayList<>());
            }
            hunter.getPokemons().add(pokemon);
            hunter.setCatchFirstPokemon(true);
            hunterRepository.save(hunter);
            pokemonRepository.save(pokemon);
            return pokemonInfo;
        }
        throw new Exception("You have already caught your first pokemon !");
    }

    @GetMapping("/api/hunters/{hunterId}")
    @ApiOperation(value = "Get my hunter profil",
            response = Hunter.class,
            responseContainer = "Hunter",
            authorizations = @Authorization(value = "Authorization"))
    public @ResponseBody Hunter getMyHunter(@ApiParam(value = "Id of hunter", required = true) @PathVariable String hunterId) {
        return hunterRepository.findOne(Long.valueOf(hunterId));
    }

}