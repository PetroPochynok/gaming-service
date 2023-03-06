package com.gamingservice.repository;

import com.gamingservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, CustomGameRepository {

    @Transactional(readOnly = true)
    List<Game> findAllByGenre(@Param("genre") String genre);

    @Transactional(readOnly = true)
    Optional<Game> findByName(@Param("name") String name);
}
