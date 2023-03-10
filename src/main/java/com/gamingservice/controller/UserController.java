package com.gamingservice.controller;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.mapper.UserMapper;
import com.gamingservice.model.User;
import com.gamingservice.model.dto.ExtendedUserDTO;
import com.gamingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // CRUD operations
    @GetMapping
    public List<User> getUsers() {
        return userService.findAllFetchUserProfileAndGames();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.findByIdFetchUserProfileAndGames(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no user with such id: %s", id)));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody ExtendedUserDTO dto) {
        User user = UserMapper.INSTANCE.extendedUserDtoToUser(dto);
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody ExtendedUserDTO dto) {
        User updatedUser = userService.update(dto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no user with such id: %s", id)));

        userService.remove(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }


    // Business logic
    @GetMapping("/richestUser")
    public User getTheRichestUser() {
        return userService.findTheRichestUser();
    }

    @GetMapping("/country")
    public List<User> findUsersByCountry(@RequestParam("country") String country) {
        return userService.findUsersByCountry(country);

    }

    @GetMapping("/splitByEmailDomain")
    public Map<String, List<User>> splitAllUsersByEmailDomain() {
        return userService.splitAllUsersByEmailDomain();
    }

    @GetMapping("/mostAmountOfGames")
    public User findUserWithTheMostAmountOfGames() {
        return userService.findUserWithTheMostAmountOfGames();
    }

    @GetMapping("/splitByCountry")
    public Map<String, List<User>> splitAllUsersByCountry() {
        return userService.splitAllUsersByCountry();
    }

    @GetMapping("/findByBalanceRange")
    public List<User> findUsersByBalanceRange(@RequestParam("min") BigDecimal min, BigDecimal max) {
        return userService.findByBalanceRange(min, max);
    }

    @GetMapping("/splitByGender")
    public Map<String, List<User>> splitAllUsersByGender() {
        return userService.splitAllUsersByGender();
    }

    @GetMapping("/buyGame")
    @ResponseStatus(HttpStatus.OK)
    public User buyGame(@RequestParam("userId") Long userId, @RequestParam("gameId") Long gameId) {
        return userService.buyGame(userId, gameId);
    }
}
