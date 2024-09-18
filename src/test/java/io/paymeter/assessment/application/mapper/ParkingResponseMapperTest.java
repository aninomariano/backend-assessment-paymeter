package io.paymeter.assessment.application.mapper;

import io.paymeter.assessment.application.exception.InvalidDateFormatException;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParkingResponseMapperTest {

    private static final String P_01 = "P01";
    private static final String FROM_RANDOM_DATE = "2024-09-14T09:00";
    private static final String TO_RANDOM_DATE = "2024-09-16T09:00";
    private static final int DURATION = 3690;
    private static final String PRICE = "106EUR";

    private ParkingResponseMapper parkingResponseMapper;

    @BeforeEach
    void setUp() {
        parkingResponseMapper = new ParkingResponseMapperImpl();
    }

    @Test
    void givenParkingResponseMapper_whenTryToMapParkingResponse_thenMustGoAllOk() {
        final var parkingCalculation = ParkingCalculation.builder()
                .parkingId(P_01)
                .from(LocalDateTime.parse(FROM_RANDOM_DATE))
                .to(LocalDateTime.parse(TO_RANDOM_DATE))
                .duration(DURATION)
                .price(PRICE)
                .build();

        final var response = parkingResponseMapper.map(parkingCalculation);

        assertThat(response.getParkingId()).isEqualTo(P_01);
        assertThat(response.getFrom()).isEqualTo(FROM_RANDOM_DATE);
        assertThat(response.getTo()).isEqualTo(TO_RANDOM_DATE);
        assertThat(response.getDuration()).isEqualTo(DURATION);
        assertThat(response.getPrice()).isEqualTo(PRICE);
    }

    @Test
    void givenNullParkingResponseMapper_whenTryToMapParkingResponse_thenMustReturnNull() {
        assertThat(parkingResponseMapper.map(null)).isNull();
    }

    @Test
    void givenParkingResponseMapper_whenTryToMapRequestWithInvalidDate_thenMustThrowRespectiveError() {
        final var parkingCalculation = ParkingCalculation.builder()
                .parkingId(P_01)
                .from(LocalDateTime.parse(FROM_RANDOM_DATE))
                .duration(DURATION)
                .price(PRICE)
                .build();

        assertThatThrownBy(() -> parkingResponseMapper.map(parkingCalculation))
                .isInstanceOf(NullPointerException.class);
    }
}
