package io.paymeter.assessment.application.exception;

import org.springframework.http.HttpStatus;

public class InvalidDateException extends ApplicationException {

    public InvalidDateException() {
        super();
    }

    public InvalidDateException(final Throwable cause) {
        super(cause);
    }

    public InvalidDateException(final String message, final Throwable cause, final HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }

}
