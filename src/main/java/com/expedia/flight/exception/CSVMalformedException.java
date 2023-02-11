package com.expedia.flight.exception;

public class CSVMalformedException extends RuntimeException {
    private static final String MESSAGE = "CSV malformed";

    public CSVMalformedException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
