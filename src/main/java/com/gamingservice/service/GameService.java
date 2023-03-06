package com.gamingservice.service;

import com.gamingservice.model.Game;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> findAll();

    Optional<Game> findById(Long id);

    void remove(Long id);

    Game save(Game game);

    Game findTheMostExpensiveGame();

    List<Game> findAllGamesByGenre(String genre);

    Game findGameByName(String name);

    List<Game> findByPriceRange(BigDecimal min, BigDecimal max);
}
