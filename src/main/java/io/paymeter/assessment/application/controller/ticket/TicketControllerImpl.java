package io.paymeter.assessment.application.controller.ticket;

import io.paymeter.assessment.application.dto.request.ParkingRequest;
import io.paymeter.assessment.application.dto.response.ParkingResponse;
import io.paymeter.assessment.domain.service.ParkingService;
import io.paymeter.assessment.infrastructure.repository.parking.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

	private final ParkingService parkingService;

	@Override
	public ResponseEntity<ParkingResponse> calculate(final ParkingRequest request) {
		return null;
	}

}
