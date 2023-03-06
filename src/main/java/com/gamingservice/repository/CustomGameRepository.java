package com.gamingservice.repository;

import com.gamingservice.model.Game;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CustomGameRepository {

    Game findTheMostExpensiveGame();

    List<Game> findByPriceRange(BigDecimal min, BigDecimal max);

    Game findLastReleasedGame();

    Map<String, List<Game>> splitAllGamesByGenre();
}
