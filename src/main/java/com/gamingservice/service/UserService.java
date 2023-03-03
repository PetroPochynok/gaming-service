package com.gamingservice.service;

import com.gamingservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    void remove(Long id);

    void save(User user);
}
