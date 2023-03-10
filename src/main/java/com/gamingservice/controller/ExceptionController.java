package com.gamingservice.controller;

import com.gamingservice.exception.EntityNotFoundException;
import com.gamingservice.exception.ErrorResponse;
import com.gamingservice.exception.NotEnoughMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        return createResponseEntity(entityNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException illegalStateException) {
        return createResponseEntity(illegalStateException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return createResponseEntity(illegalArgumentException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughMoneyException(NotEnoughMoneyException notEnoughMoneyException) {
        return createResponseEntity(notEnoughMoneyException, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(RuntimeException exc, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(httpStatus.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
