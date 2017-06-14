package fr.norsys.battle;

import com.google.common.collect.Lists;
import fr.norsys.hunters.HunterRepository;
import fr.norsys.models.Battle;
import fr.norsys.models.Hunter;
import fr.norsys.models.PokemonInfo;
import fr.norsys.pokemons.PokemonSparkRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Api(value = "/api/battles", description = "Operations about battles")
public class BattleController {

    @Autowired
    BattleService battleService;

    @Autowired
    HunterRepository hunterRepository;

    List<Hunter> hunters = new ArrayList<>();


    @PostMapping("/api/battles/ready")
    @ApiOperation(value = "Ready for start battle",
            authorizations = @Authorization(value = "Authorization"))
    public void readyToBattle(@ApiParam(value = "Hunter to register", required = true) @RequestBody Hunter hunter) {
        hunters.add(hunterRepository.findOne(hunter.getId()));
    }

    @GetMapping("/api/battles/opponent")
    @ApiOperation(value = "Ready for start battle",
            authorizations = @Authorization(value = "Authorization"))
    public List<Hunter> findAllOpponent() {
        return hunters;
    }


    @MessageMapping("/api/battle/start")
    @SendTo("/api/battle/results")
    public Battle battle(Battle battle) throws Exception {
        battleService.launchAttack(battle);
        if(battle.getWin() != null && battle.getWin().booleanValue()){
            hunters.remove(battle.getHunterAttacker());
        }
        return battle;
    }

}
