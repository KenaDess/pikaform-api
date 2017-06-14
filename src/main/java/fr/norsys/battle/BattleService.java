package fr.norsys.battle;

import com.google.common.collect.Lists;
import fr.norsys.models.Battle;
import fr.norsys.models.Pokemon;
import fr.norsys.pokemons.PokemonRepository;
import fr.norsys.pokemons.PokemonSparkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BattleService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    PokemonSparkRepository pokemonSparkRepository;


    public void launchAttack(Battle battle){
        if(!battle.isInit()) {
            battle.setAttackerInfo(pokemonSparkRepository.findPokemonsById(battle.getAttacker().getPokemonId()));
            battle.setDefenderInfo(pokemonSparkRepository.findPokemonsById(battle.getDefender().getPokemonId()));
            battle.setDefenderHP(Float.valueOf(battle.getDefenderInfo().getHp()));
            battle.setAttackerHP(Float.valueOf(battle.getAttackerInfo().getHp()));
        }
        battle.setDefenderHP(battle.getDefenderHP()-calculateDamage(battle));
        if(battle.getDefenderHP()<0){
            battle.setWin(true);
            Pokemon pokemon = battle.getAttacker();
            pokemon.setLevel(pokemon.getLevel()+1);
            pokemonRepository.save(pokemon);
        }
    }

    private Float calculateDamage(Battle battle){
        return calculatePokemonLevel(battle)*calculateModifier(battle);
    }

    private Float calculateModifier(Battle battle){
        return (0.75f*1.5f*((new Random().nextFloat()*(1.0f-0.75f)+0.75f)));
    }

    private Float calculatePokemonLevel(Battle battle){
        Float varLevel=((2f*battle.getAttacker().getLevel())/5f)+2f;
        return ((varLevel*Float.valueOf(battle.getMoveToGo().getPower())*(Float.valueOf(battle.getAttackerInfo().getAttack())/Float.valueOf(battle.getDefenderInfo().getDefense())))/50f)+2f;
    }
}
