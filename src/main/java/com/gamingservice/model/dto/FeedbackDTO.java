package com.gamingservice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedbackDTO {

    private Long id;
    private String message;
    private int rating;
    private Long gameId;
    private Long userId;
}
