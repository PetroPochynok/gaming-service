package com.gamingservice.service;

import com.gamingservice.model.User;
import com.gamingservice.model.dto.ExtendedUserDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    void remove(Long id);

    void save(User user);

    User update(ExtendedUserDTO dto, Long id);


    List<User> findAllFetchUserProfileAndGames();

    Optional<User> findByIdFetchUserProfileAndGames(Long id);

    User findTheRichestUser();

    List<User> findUsersByCountry(String country);

    Map<String, List<User>> splitAllUsersByEmailDomain();

    User findUserWithTheMostAmountOfGames();

    Map<String, List<User>> splitAllUsersByCountry();

    List<User> findByBalanceRange(BigDecimal min, BigDecimal max);

    Map<String, List<User>> splitAllUsersByGender();

    User buyGame(Long userId, Long gameId);
}
