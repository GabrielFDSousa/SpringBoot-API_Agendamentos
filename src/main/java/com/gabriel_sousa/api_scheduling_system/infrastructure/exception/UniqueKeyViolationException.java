package com.gabriel_sousa.api_scheduling_system.infrastructure.exception;

public class UniqueKeyViolationException extends RuntimeException{
    public UniqueKeyViolationException(String message){
        super(message);
    }
}
