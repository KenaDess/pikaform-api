package fr.norsys.battle;


import fr.norsys.models.Battle;
import fr.norsys.models.Move;
import fr.norsys.models.Pokemon;
import fr.norsys.models.PokemonInfo;
import fr.norsys.pokemons.PokemonRepository;
import fr.norsys.pokemons.PokemonSparkRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BattleServiceTest {

    @InjectMocks
    private BattleService battleService;

    @Mock
    PokemonRepository pokemonRepository;

    @Mock
    private PokemonSparkRepository pokemonSparkRepository;

    @Test
    public void should_substraite_hp_with_specific_move(){
        PokemonInfo attackerInfo = PokemonInfo.builder().attack("49").defense("49").hp("45").build();
        PokemonInfo defenderInfo = PokemonInfo.builder().attack("111").defense("90").hp("106").build();

        Pokemon attacker = Pokemon.builder().id(1).level(1).pokemonId(1).build();
        Pokemon defender = Pokemon.builder().id(1).level(1).pokemonId(150).build();

        Move move =Move.builder().crit("0").power("75").build();

        Battle battle = new Battle();
        battle.setAttacker(attacker);
        battle.setAttackerInfo(attackerInfo);
        battle.setDefender(defender);
        battle.setDefenderInfo(defenderInfo);
        battle.setMoveToGo(move);

        when(pokemonSparkRepository.findPokemonsById(battle.getAttacker().getPokemonId())).thenReturn(attackerInfo);
        when(pokemonSparkRepository.findPokemonsById(battle.getDefender().getPokemonId())).thenReturn(defenderInfo);

        battleService.launchAttack(battle);

        assertEquals(battle.getDefenderHP(),new Float(102.08673));
    }

    @Test
    public void should_substraite_hp_with_other_pokemon(){
        PokemonInfo attackerInfo = PokemonInfo.builder().attack("123").defense("163").hp("45").build();
        PokemonInfo defenderInfo = PokemonInfo.builder().attack("111").defense("90").hp("106").build();

        Pokemon attacker = Pokemon.builder().id(1).level(75).pokemonId(1).build();
        Pokemon defender = Pokemon.builder().id(1).level(1).pokemonId(150).build();

        Move move =Move.builder().crit("0").power("75").build();

        Battle battle = new Battle();
        battle.setAttacker(attacker);
        battle.setAttackerInfo(attackerInfo);
        battle.setDefender(defender);
        battle.setDefenderInfo(defenderInfo);
        battle.setMoveToGo(move);

        when(pokemonSparkRepository.findPokemonsById(battle.getAttacker().getPokemonId())).thenReturn(attackerInfo);
        when(pokemonSparkRepository.findPokemonsById(battle.getDefender().getPokemonId())).thenReturn(defenderInfo);

        battleService.launchAttack(battle);

        assertEquals(battle.getDefenderHP(),new Float(37.317993));
    }
}
