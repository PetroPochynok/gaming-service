package com.gamingservice.repository;

import com.gamingservice.model.Game;

import java.math.BigDecimal;
import java.util.List;

public interface CustomGameRepository {

    Game findTheMostExpensiveGame();

    List<Game> findByPriceRange(BigDecimal min, BigDecimal max);

    Game findLastReleasedGame();
}
