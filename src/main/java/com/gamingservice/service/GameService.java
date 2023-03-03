package com.gamingservice.service;

import com.gamingservice.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> findAll();

    Optional<Game> findById(Long id);

    void remove(Long id);

    Game save(Game game);
}
