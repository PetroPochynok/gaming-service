package com.gamingservice.controller;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.model.Game;
import com.gamingservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // CRUD operations
    @GetMapping
    public List<Game> getGames() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable("id") Long id) {
        return gameService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no game with such id: %s", id)));
    }

    @PostMapping()
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        gameService.save(game);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable("id") Long id, @RequestBody Game game) {
        if (!Objects.equals(id, game.getId())) {
            throw new IllegalStateException("Id parameter does not match game body value");
        }
        Game updatedGame = gameService.save(game);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedGame);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Game> deleteGame(@PathVariable("id") Long id) {
        Game game = gameService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no game with such id: %s", id)));

        gameService.remove(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(game);
    }

    // Business logic
    @GetMapping("/mostExpensiveGame")
    public Game getTheMostExpensiveGame() {
        return gameService.findTheMostExpensiveGame();
    }

    @GetMapping("/genre")
    public List<Game> findAllGamesByGenre(@RequestParam("genre") String genre) {
        return gameService.findAllGamesByGenre(genre);
    }

    @GetMapping("/name")
    public Game findGameByName(@RequestParam("name") String name) {
        return gameService.findGameByName(name);
    }

    @GetMapping("/findByPriceRange")
    public List<Game> findGamesByPriceRange(@RequestParam("min") BigDecimal min, @RequestParam("max") BigDecimal max) {
        return gameService.findByPriceRange(min, max);
    }

    @GetMapping("/lastReleasedGame")
    public Game findLastReleasedGame() {
        return gameService.findLastReleasedGame();
    }
}
