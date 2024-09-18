package io.paymeter.assessment.application.controller;

import io.paymeter.assessment.application.exception.InvalidDateException;
import io.paymeter.assessment.application.exception.InvalidDateFormatException;
import io.paymeter.assessment.application.exception.ParkingNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ControllerExceptionHandlerTest {

    private static final String RANDOM_MESSAGE = "randomMessage";

    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void setUp() {
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    void givenInvalidDateFormatException_whenHandleException_thenMustReturnResponseEntityWithRespectiveError() {
        final var exception = new InvalidDateFormatException(RANDOM_MESSAGE);
        final var webRequest = mock(WebRequest.class);

        final var response = controllerExceptionHandler.handleInvalidDateFormatException(exception, webRequest);

        assertThat(response.getBody().getMessage()).isEqualTo(RANDOM_MESSAGE);
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void givenInvalidDateException_whenHandleException_thenMustReturnResponseEntityWithRespectiveError() {
        final var exception = new InvalidDateException(RANDOM_MESSAGE);
        final var webRequest = mock(WebRequest.class);

        final var response = controllerExceptionHandler.handleInvalidDateException(exception, webRequest);

        assertThat(response.getBody().getMessage()).isEqualTo(RANDOM_MESSAGE);
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void givenParkingNotFoundException_whenHandleException_thenMustReturnResponseEntityWithRespectiveError() {
        final var exception = new ParkingNotFoundException(RANDOM_MESSAGE);
        final var webRequest = mock(WebRequest.class);

        final var response = controllerExceptionHandler.handleParkingNotFoundException(exception, webRequest);

        assertThat(response.getBody().getMessage()).isEqualTo(RANDOM_MESSAGE);
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
