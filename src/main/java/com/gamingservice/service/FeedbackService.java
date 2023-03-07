package com.gamingservice.service;

import com.gamingservice.model.Feedback;
import com.gamingservice.model.dto.FeedbackDTO;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    List<Feedback> findAll();

    Optional<Feedback> findById(Long id);

    void remove(Long id);

    Feedback save(FeedbackDTO dto);

    Feedback update(Long id, FeedbackDTO dto);

    List<Feedback> findAllFetchGameAndUserWithGames();

    List<Feedback> findAllUserFeedbacksByIdFetchGameAndUser(Long userId);

    List<Feedback> findAllFeedbacksOfOneGameById(Long id);

    Double getAverageRatingOfGameById(Long id);

    Optional<Feedback> findByIdFetchGameAndUserWithUserProfileAndGames(Long id);
}
