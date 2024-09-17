package io.paymeter.assessment.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private HttpStatus httpStatus;

    public ApplicationException() {
        super();
    }

    public ApplicationException(final Throwable cause) {
        super(cause);
    }

    public ApplicationException(final Throwable cause, final HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public ApplicationException(final String message, final Throwable cause, final HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

}
