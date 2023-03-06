package com.gamingservice.repository;

import com.gamingservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

    @Query("select DISTINCT u from User u join fetch u.userProfile left join fetch u.games")
    @Transactional(readOnly = true)
    List<User> findAllFetchUserProfileAndGames();

    @Transactional(readOnly = true)
    @Query("select u from User u join fetch u.userProfile left join fetch u.games where u.id=:id")
    Optional<User> findByIdFetchUserProfileAndGames(@Param("id") Long id);

}
