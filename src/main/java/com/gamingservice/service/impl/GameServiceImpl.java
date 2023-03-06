package com.gamingservice.service.impl;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.model.Game;
import com.gamingservice.repository.GameRepository;
import com.gamingservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game findTheMostExpensiveGame() {
        return gameRepository.findTheMostExpensiveGame();
    }

    @Override
    public List<Game> findAllGamesByGenre(String genre) {
        List<Game> list = gameRepository.findAllByGenre(genre);
        if (!list.isEmpty()) {
            return list;
        }
        throw new EntityNotFoundException(String.format("There are no games with genre: %s", genre));
    }

    @Override
    public Game findGameByName(String name) {
        return gameRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no game with name: %s", name)));
    }

    @Override
    public List<Game> findByPriceRange(BigDecimal min, BigDecimal max) {
        List<Game> list = gameRepository.findByPriceRange(min, max);
        if (!list.isEmpty()) {
            return list;
        }
        throw new EntityNotFoundException(String.format("Can't find games between %.2f and %.2f range", min, max));
    }
}
