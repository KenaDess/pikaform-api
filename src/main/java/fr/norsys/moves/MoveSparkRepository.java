package fr.norsys.moves;

import fr.norsys.models.Move;
import fr.norsys.models.PokemonInfo;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class MoveSparkRepository {

    Dataset<Move> moves;

    MoveSparkRepository(){
        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .getOrCreate();
        moves = spark.read()
                .format("com.databricks.spark.csv")
                .option("header", "true")
                .load("src/main/resources/moves.csv")
                .as(Encoders.bean(Move.class));

    }


    public List<Move> findAllMove() {
        return moves.collectAsList();
    }
}