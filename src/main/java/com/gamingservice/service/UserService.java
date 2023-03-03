package com.gamingservice.service;

import com.gamingservice.model.User;
import com.gamingservice.model.dto.UserAndUserProfileDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    void remove(Long id);

    void save(User user);

    User update(UserAndUserProfileDTO dto, Long id);


    List<User> findAllFetchUserProfileAndGames();

    Optional<User> findByIdFetchUserProfileAndGames(Long id);

    Optional<User> findByIdFetchUserProfile(Long id);
}
