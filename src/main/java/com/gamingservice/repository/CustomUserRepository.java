package com.gamingservice.repository;

import com.gamingservice.model.User;

import java.util.List;
import java.util.Map;

public interface CustomUserRepository {
    User findTheRichestUser();

    List<User> findUsersByCountry(String country);

    Map<String, List<User>> splitAllUsersByEmailDomain();

    User findUserWithTheMostAmountOfGames();

    Map<String, List<User>> splitAllUsersByCountry();
}
