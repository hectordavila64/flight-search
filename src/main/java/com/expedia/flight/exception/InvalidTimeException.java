package com.expedia.flight.exception;

public class InvalidTimeException extends RuntimeException {
    private static final String MESSAGE = "Invalid Time";

    public InvalidTimeException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
