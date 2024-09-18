package io.paymeter.assessment.application.exception;

import org.springframework.http.HttpStatus;

public class InvalidDateFormatException extends ApplicationException {

    public InvalidDateFormatException(final String applicationMessage) {
        super(applicationMessage);
    }

    public InvalidDateFormatException(final Throwable cause) {
        super(cause);
    }

    public InvalidDateFormatException(final String message, final Throwable cause, final HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }

}
