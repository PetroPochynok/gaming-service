package com.gamingservice.service.impl;

import com.gamingservice.model.Game;
import com.gamingservice.repository.GameRepository;
import com.gamingservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void save(Game game) {
        gameRepository.save(game);
    }
}
