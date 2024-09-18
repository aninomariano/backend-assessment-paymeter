package io.paymeter.assessment.application.controller.ticket;

import io.paymeter.assessment.application.dto.request.ParkingRequest;
import io.paymeter.assessment.application.dto.response.ParkingResponse;
import io.paymeter.assessment.application.mapper.ParkingRequestMapper;
import io.paymeter.assessment.application.mapper.ParkingResponseMapper;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    private static final String P_01 = "P01";
    private static final String FROM_RANDOM_DATE = "2024-09-14T09:00:00";
    private static final String TO_RANDOM_DATE = "2024-09-16T09:00:00";
    private static final int DURATION = 3690;
    private static final String PRICE = "106EUR";

    @Mock
    private ParkingService parkingService;

    @Mock
    private ParkingRequestMapper parkingRequestMapper;

    @Mock
    private ParkingResponseMapper parkingResponseMapper;

    @InjectMocks
    private TicketControllerImpl ticketController;

    @Test
    void givenParkingRequest_whenTryToExecuteCalculation_thenMustExecuteAllOk() {
        final var parkingRequest = new ParkingRequest(P_01, FROM_RANDOM_DATE, TO_RANDOM_DATE);
        final var parkingResponse = new ParkingResponse(P_01, FROM_RANDOM_DATE, TO_RANDOM_DATE, DURATION, PRICE);
        final var parkingCalculation = ParkingCalculation.builder()
                .parkingId(P_01)
                .build();

        when(parkingRequestMapper.map(parkingRequest)).thenReturn(parkingCalculation);
        when(parkingResponseMapper.map(parkingCalculation)).thenReturn(parkingResponse);

        final var response = ticketController.calculate(parkingRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(parkingResponse);
        verify(parkingService, only()).calculateParking(parkingCalculation);
    }
}
