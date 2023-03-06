package com.gamingservice.repository;

import com.gamingservice.model.Game;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Repository
@Transactional
public class CustomGameRepositoryImpl implements CustomGameRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Game findTheMostExpensiveGame() {
        return entityManager.createQuery("select g from Game g left join fetch g.users order by g.price desc", Game.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Game> findByPriceRange(BigDecimal min, BigDecimal max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("value min can not be higher than value max");
        }
        return entityManager.createQuery("select g from Game g left join fetch g.users where g.price >= :min AND g.price <= :max", Game.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Game findLastReleasedGame() {
        return entityManager.createQuery("select g from Game g left join fetch g.users order by g.releaseDate desc", Game.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, List<Game>> splitAllGamesByGenre() {
        return entityManager.createQuery("select g from Game g left join fetch g.users", Game.class)
                .getResultList()
                .stream()
                .collect(groupingBy(Game::getGenre));
    }
}
