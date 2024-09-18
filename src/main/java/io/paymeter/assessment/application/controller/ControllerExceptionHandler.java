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
    public ResponseEntity<ErrorDetail> handleInvalidDateFormatException(final InvalidDateFormatException ex, final WebRequest request) {
        final ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getApplicationMessage(),
                request.getDescription(false), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParkingNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleParkingNotFoundException(final ParkingNotFoundException ex, final WebRequest request) {
        final ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getApplicationMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ErrorDetail> handleInvalidDateException(final InvalidDateException ex, final WebRequest request) {
        final ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getApplicationMessage(),
                request.getDescription(false), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

}
