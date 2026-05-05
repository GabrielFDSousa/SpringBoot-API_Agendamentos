package com.gabriel_sousa.api_scheduling_system.infrastructure.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionResponse(
        HttpStatus status,
        int code,
        String message,
        LocalDateTime timestamp
) {
}
