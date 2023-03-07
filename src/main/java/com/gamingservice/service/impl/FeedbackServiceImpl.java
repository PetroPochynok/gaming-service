package com.gamingservice.service.impl;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.model.Feedback;
import com.gamingservice.model.Game;
import com.gamingservice.model.User;
import com.gamingservice.model.dto.FeedbackDTO;
import com.gamingservice.repository.FeedbackRepository;
import com.gamingservice.repository.GameRepository;
import com.gamingservice.repository.UserRepository;
import com.gamingservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Feedback save(FeedbackDTO dto) {
        Long userId = dto.getUserId();
        Long gameId = dto.getGameId();
        int rating = dto.getRating();

        if (rating > 5 || 0 > rating) {
            throw new IllegalStateException("rating should be in range from 0 to 5");
        }

        User user = userRepository.findByIdFetchUserProfileAndGames(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no user with id: %s", userId)));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no game with id: %s", gameId)));

        if (!user.getGames().contains(game)) {
            throw new IllegalStateException("user can't leave a feedback if he doesn't have this game");
        }

        List<Feedback> allUserFeedbacks = feedbackRepository.findAllUserFeedbacksByIdFetchGameAndUser(userId);
        boolean userAlreadyLeaveFeedbackAboutGame = allUserFeedbacks.stream()
                .anyMatch(f -> f.getGame().getId().equals(gameId));

        if (userAlreadyLeaveFeedbackAboutGame) {
            throw new IllegalStateException(String.format("user with id %s has already left a feedback about game with id %s", userId, gameId));
        }

        Feedback feedback = new Feedback();
        feedback.setMessage(dto.getMessage());
        feedback.setRating(rating);
        feedback.setUser(user);
        feedback.setGame(game);

        feedbackRepository.save(feedback);
        return feedback;
    }

    @Override
    @Transactional
    public Feedback update(Long id, FeedbackDTO dto) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no feedback with id: %s", id)));

        // allow only to change rating and message
        feedback.setMessage(dto.getMessage());
        feedback.setRating(dto.getRating());
        return feedback;
    }

    @Override
    public List<Feedback> findAllFetchGameAndUserWithGames() {
        return feedbackRepository.findAllFetchGameAndUserWithGames();
    }

    @Override
    public List<Feedback> findAllUserFeedbacksByIdFetchGameAndUser(Long userId) {
        return feedbackRepository.findAllUserFeedbacksByIdFetchGameAndUser(userId);
    }
}
