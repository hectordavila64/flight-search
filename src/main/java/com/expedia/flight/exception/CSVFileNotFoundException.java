package com.expedia.flight.exception;

public class CSVFileNotFoundException extends RuntimeException {
    private static final String MESSAGE = "CSV flight file not found";

    public CSVFileNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
