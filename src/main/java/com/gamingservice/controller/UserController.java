package com.gamingservice.controller;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.mapper.UserMapper;
import com.gamingservice.model.User;
import com.gamingservice.model.dto.UserAndUserProfileDTO;
import com.gamingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no user with such id: %s", id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserAndUserProfileDTO dto) {
        User user = UserMapper.INSTANCE.UserAndUserProfileDtoToUser(dto);
        userService.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserAndUserProfileDTO dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalStateException("Id parameter does not match user body value");
        }
        User user = UserMapper.INSTANCE.UserAndUserProfileDtoToUser(dto);
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
    }
}
