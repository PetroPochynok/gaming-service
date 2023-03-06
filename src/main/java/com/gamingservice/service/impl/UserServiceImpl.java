package com.gamingservice.service.impl;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.model.User;
import com.gamingservice.model.UserProfile;
import com.gamingservice.model.dto.UserAndUserProfileDTO;
import com.gamingservice.repository.UserRepository;
import com.gamingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    @Override
    @Transactional
    public User update(UserAndUserProfileDTO dto, Long id) {
        User user = userRepository.findByIdFetchUserProfileAndGames(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no user with such id: %s", id)));

        // we don't allow to change our email because our equalsAndHashCode based on this field
        // and also don't allow to change field createdAt to keep things right
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBalance(dto.getBalance());
        user.setGender(dto.getGender());

        UserProfile userProfile = user.getUserProfile();
        userProfile.setCountry(dto.getCountry());
        userProfile.setCity(dto.getCity());
        userProfile.setStreet(dto.getStreet());
        return user;
    }

    @Override
    public List<User> findAllFetchUserProfileAndGames() {
        return userRepository.findAllFetchUserProfileAndGames();
    }

    @Override
    public Optional<User> findByIdFetchUserProfileAndGames(Long id) {
        return userRepository.findByIdFetchUserProfileAndGames(id);
    }

    @Override
    public User findTheRichestUser() {
        return userRepository.findTheRichestUser();
    }

    @Override
    public List<User> findUsersByCountry(String country) {
        List<User> list = userRepository.findUsersByCountry(country);
        if (!list.isEmpty()) {
            return list;
        }
        throw new EntityNotFoundException(String.format("there are no users with country: %s", country));
    }

    @Override
    public Map<String, List<User>> splitAllUsersByEmailDomain() {
        return userRepository.splitAllUsersByEmailDomain();
    }

    @Override
    public User findUserWithTheMostAmountOfGames() {
        return userRepository.findUserWithTheMostAmountOfGames();
    }

    @Override
    public Map<String, List<User>> splitAllUsersByCountry() {
        return userRepository.splitAllUsersByCountry();
    }

    @Override
    public List<User> findByBalanceRange(BigDecimal min, BigDecimal max) {
        List<User> list = userRepository.findByBalanceRange(min, max);
        if (!list.isEmpty()) {
            return list;
        }
        throw new EntityNotFoundException(String.format("Can't find users between %.2f and %.2f balance range", min, max));
    }

}
