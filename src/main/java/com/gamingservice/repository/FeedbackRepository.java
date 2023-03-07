package com.gamingservice.repository;

import com.gamingservice.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Transactional(readOnly = true)
    @Query("select DISTINCT f from Feedback f join fetch f.game join fetch f.user u join fetch u.userProfile left join fetch u.games")
    List<Feedback> findAllFetchGameAndUserWithGames();

    @Transactional(readOnly = true)
    @Query("select f from Feedback f join fetch f.game join fetch f.user u join fetch u.userProfile where f.user.id=:userId")
    List<Feedback> findAllUserFeedbacksByIdFetchGameAndUser(@Param("userId") Long id);

    @Transactional(readOnly = true)
    @Query("select f from Feedback f where f.game.id=:gameId")
    List<Feedback> findAllFeedbacksOfOneGameById(@Param("gameId") Long id);

    @Transactional(readOnly = true)
    @Query("select AVG(f.rating) from Feedback f where f.game.id=:gameId")
    Double getAverageRatingOfGameById(@Param("gameId") Long id);

    @Transactional(readOnly = true)
    @Query("select f from Feedback f join fetch f.game join fetch f.user u join fetch u.userProfile join fetch u.games where f.id=:id")
    Optional<Feedback> findByIdFetchGameAndUserWithUserProfileAndGames(@Param("id") Long id);
}
