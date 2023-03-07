package com.gamingservice.controller;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.model.Feedback;
import com.gamingservice.model.dto.FeedbackDTO;
import com.gamingservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<Feedback> getFeedbacks() {
        return feedbackService.findAllFetchGameAndUserWithGames();
    }

    @GetMapping("/{id}")
    public Feedback getFeedback(@PathVariable("id") Long id) {
        return feedbackService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no feedback with id: %s", id)));
    }

    @PostMapping
    public ResponseEntity<Feedback> saveFeedback(@RequestBody FeedbackDTO dto) {
        Feedback savedFeedback = feedbackService.save(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedFeedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable("id") Long id, @RequestBody FeedbackDTO dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalStateException("Id parameter does not match feedback value");
        }
        Feedback updatedFeedback = feedbackService.update(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedFeedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable("id") Long id) {
        Feedback feedback = feedbackService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no feedback with id: %s", id)));

        feedbackService.remove(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(feedback);
    }
}
