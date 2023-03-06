package com.gamingservice.repository;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Repository
@Transactional
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public User findTheRichestUser() {
        return entityManager.createQuery("select u from User u join fetch u.userProfile join fetch u.games order by u.balance desc", User.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findUsersByCountry(String country) {
        TypedQuery<User> query = entityManager
                .createQuery("select DISTINCT u from User u join fetch u.userProfile left join fetch u.games where u.userProfile.country=:country", User.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, List<User>> splitAllUsersByEmailDomain() {
        List<User> list = entityManager.createQuery("select DISTINCT u from User u join fetch u.userProfile left join fetch u.games", User.class).getResultList();
        return list.stream()
                .collect(groupingBy(u -> u.getEmail().split("@")[1]));
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserWithTheMostAmountOfGames() {
        return entityManager.createQuery("select u from User u join fetch u.userProfile left join fetch u.games", User.class)
                .getResultList()
                .stream()
                .max(Comparator.comparing(u -> u.getGames().size()))
                .orElseThrow(() -> new EntityNotFoundException("can't find user with the most amount of games"));
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, List<User>> splitAllUsersByCountry() {
        return entityManager.createQuery("select DISTINCT u from User u join fetch u.userProfile left join fetch u.games", User.class)
                .getResultList()
                .stream()
                .collect(groupingBy(acc -> acc.getUserProfile().getCountry()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByBalanceRange(BigDecimal min, BigDecimal max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("value min can not be higher than value max");
        }
        return entityManager
                .createQuery("select DISTINCT u from User u join fetch u.userProfile left join fetch u.games where u.balance >= :min and u.balance <= :max order by u.balance asc", User.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, List<User>> splitAllUsersByGender() {
        return entityManager
                .createQuery("select DISTINCT u from User u join fetch u.userProfile left join fetch u.games", User.class)
                .getResultList()
                .stream()
                .collect(groupingBy(User::getGender));
    }
}
