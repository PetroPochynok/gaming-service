package com.gamingservice.repository;

import com.gamingservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join fetch u.userProfile left join fetch u.games")
    List<User> findAllFetchUserProfileAndGames();

    @Query("select u from User u left join fetch u.userProfile left join fetch u.games where u.id=:id")
    Optional<User> findByIdFetchUserProfileAndGames(@Param("id") Long id);

    @Query("select u from User u left join fetch u.userProfile where u.id=:id")
    Optional<User> findByIdFetchUserProfile(@Param("id") Long id);
}
