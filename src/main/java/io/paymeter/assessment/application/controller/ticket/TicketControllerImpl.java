package io.paymeter.assessment.application.controller.ticket;

import io.paymeter.assessment.application.dto.request.ParkingRequest;
import io.paymeter.assessment.application.dto.response.ParkingResponse;
import io.paymeter.assessment.application.mapper.ParkingMapper;
import io.paymeter.assessment.domain.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

	private final ParkingService parkingService;

	private final ParkingMapper parkingMapper;

	@Override
	public ResponseEntity<ParkingResponse> calculate(final ParkingRequest request) {
		parkingService.calculateParking(parkingMapper.map(request));
		return ResponseEntity.of(Optional.of(new ParkingResponse()));
	}

}
