package com.gabriel_sousa.api_scheduling_system.infrastructure.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UniqueKeyViolationException.class)
    public ResponseEntity<ExceptionResponse> handleUniqueKeyViolationException(UniqueKeyViolationException ex){
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e-> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                message,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex){
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    };
}
