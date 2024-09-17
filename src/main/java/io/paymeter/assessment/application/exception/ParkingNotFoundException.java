package io.paymeter.assessment.application.exception;

import org.springframework.http.HttpStatus;

public class ParkingNotFoundException extends ApplicationException {

    public ParkingNotFoundException() {
        super();
    }

    public ParkingNotFoundException(final Throwable cause) {
        super(cause);
    }

    public ParkingNotFoundException(final String message, final Throwable cause, final HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }

}
