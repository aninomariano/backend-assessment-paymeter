package io.paymeter.assessment.application.mapper;

import io.paymeter.assessment.application.dto.request.ParkingRequest;
import io.paymeter.assessment.application.exception.InvalidDateFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParkingRequestMapperTest {

    private static final String P_01 = "P01";
    private static final String FROM_RANDOM_DATE = "2024-09-14T09:00:00";
    private static final String TO_RANDOM_DATE = "2024-09-16T09:00:00";
    private static final String INVALID_DATE = "2024-09-16asd09:00:00";

    private ParkingRequestMapper parkingRequestMapper;

    @BeforeEach
    void setUp() {
        parkingRequestMapper = new ParkingRequestMapperImpl();
    }

    @Test
    void givenParkingRequestMapper_whenTryToMapRequest_thenMustGoAllOk() {
        final var parkingRequest = new ParkingRequest(P_01, FROM_RANDOM_DATE, TO_RANDOM_DATE);

        final var response = parkingRequestMapper.map(parkingRequest);

        assertThat(response.getParkingId()).isEqualTo(P_01);
        assertThat(response.getFrom()).isEqualTo(FROM_RANDOM_DATE);
        assertThat(response.getTo()).isEqualTo(TO_RANDOM_DATE);
    }

    @Test
    void givenNullParkingRequestMapper_whenTryToMapRequest_thenMustReturnNull() {
        assertThat(parkingRequestMapper.map(null)).isNull();
    }

    @Test
    void givenParkingRequestMapper_whenTryToMapRequestWithInvalidDate_thenMustThrowRespectiveError() {
        final var parkingRequest = new ParkingRequest(P_01, INVALID_DATE, TO_RANDOM_DATE);

        assertThatThrownBy(() -> parkingRequestMapper.map(parkingRequest))
                .isInstanceOf(InvalidDateFormatException.class);
    }
}
