package io.paymeter.assessment.application.controller.ticket;

import io.paymeter.assessment.application.dto.request.ParkingRequest;
import io.paymeter.assessment.application.dto.response.ParkingResponse;
import io.paymeter.assessment.application.mapper.ParkingRequestMapper;
import io.paymeter.assessment.application.mapper.ParkingResponseMapper;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

	private final ParkingService parkingService;

	private final ParkingRequestMapper parkingRequestMapper;

	private final ParkingResponseMapper parkingResponseMapper;

	@Override
	public ResponseEntity<ParkingResponse> calculate(final ParkingRequest request) {
		final ParkingCalculation parkingCalculation = parkingRequestMapper.map(request);
		parkingService.calculateParking(parkingCalculation);
		return ResponseEntity.of(Optional.of(parkingResponseMapper.map(parkingCalculation)));
	}

}
