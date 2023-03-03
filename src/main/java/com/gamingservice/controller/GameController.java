package com.gamingservice.controller;

import com.gamingservice.exception.GameNotFoundException;
import com.gamingservice.model.Game;
import com.gamingservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getGames() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable("id") Long id) {
        return gameService.findById(id)
                .orElseThrow(() -> new GameNotFoundException(String.format("There is no game with such id: %s", id)));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveGame(@RequestBody Game game) {
        gameService.save(game);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@PathVariable("id") Long id, @RequestBody Game game) {
        if (!Objects.equals(id, game.getId())) {
            throw new IllegalStateException("Id parameter does not match game body value");
        }
        gameService.save(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") Long id) {
        gameService.remove(id);
    }
}
