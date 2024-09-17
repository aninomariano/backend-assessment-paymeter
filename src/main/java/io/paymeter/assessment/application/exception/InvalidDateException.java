package io.paymeter.assessment.application.exception;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException(final Throwable cause) {
        super(cause);
    }

    public InvalidDateException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
