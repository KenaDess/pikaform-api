package fr.norsys.pokemons;

import fr.norsys.models.Move;
import fr.norsys.models.PokemonInfo;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonSparkRepository{

    Dataset<PokemonInfo> pokemonDatas;

    PokemonSparkRepository(){
        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .getOrCreate();
        pokemonDatas = spark.read()
                .format("com.databricks.spark.csv")
                .option("header", "true")
                .load("src/main/resources/pokemon.csv")
                .as(Encoders.bean(PokemonInfo.class));

    }

    public List<PokemonInfo> findAllPokemonsById(final List<Integer> pokemonsId){
        return pokemonDatas.where(new Column("id").isin(pokemonsId.toArray())).collectAsList();
    }

    public PokemonInfo findPokemonsById(final Integer pokemonsId){
        return pokemonDatas.where(new Column("id").equalTo(pokemonsId)).first();
    }



    public List<PokemonInfo> findAllPokemons() {
        return pokemonDatas.collectAsList();
    }

}