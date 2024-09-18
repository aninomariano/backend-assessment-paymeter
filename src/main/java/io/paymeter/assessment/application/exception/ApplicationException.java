package io.paymeter.assessment.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private HttpStatus httpStatus;
    private String applicationMessage;

    public ApplicationException(final String applicationMessage) {
        super();
        this.applicationMessage = applicationMessage;
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
