package fr.norsys.moves;

import fr.norsys.hunters.HunterRepository;
import fr.norsys.models.Hunter;
import fr.norsys.models.Move;
import fr.norsys.models.Pokemon;
import fr.norsys.models.PokemonInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
@Api(value = "/api/moves", description = "Operations about moves")
public class MoveController {

    @Autowired
    HunterRepository hunterRepository;


    @Autowired
    MoveSparkRepository moveSparkRepository;


    @GetMapping("/api/moves")
    @ApiOperation(value = "Finds all pokemons",
            response = Move.class,
            responseContainer = "List",
            authorizations = @Authorization(value = "Authorization"))
    public @ResponseBody List<Move> findAll() {
        return moveSparkRepository.findAllMove();
    }
}
