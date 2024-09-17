package io.paymeter.assessment.application.controller;

import io.paymeter.assessment.application.dto.ErrorDetail;
import io.paymeter.assessment.application.exception.InvalidDateException;
import io.paymeter.assessment.application.exception.InvalidDateFormatException;
import io.paymeter.assessment.application.exception.ParkingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<?> handleInvalidDateFormatException(final InvalidDateFormatException ex, final WebRequest request) {
        final ErrorDetail errorDetail = new ErrorDetail(new Date(), "The date sent does not comply with ISO 8601.",
                request.getDescription(false), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParkingNotFoundException.class)
    public ResponseEntity<?> handleParkingNotFoundException(final ParkingNotFoundException ex, final WebRequest request) {
        final ErrorDetail errorDetail = new ErrorDetail(new Date(), "Parking not found.",
                request.getDescription(false), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<?> handleInvalidDateException(final InvalidDateException ex, final WebRequest request) {
        final ErrorDetail errorDetail = new ErrorDetail(new Date(), "The parking end date is not correct.",
                request.getDescription(false), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

}
