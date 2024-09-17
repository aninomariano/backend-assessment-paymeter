package io.paymeter.assessment.application.controller.ticket;

import io.paymeter.assessment.application.dto.request.ParkingRequest;
import io.paymeter.assessment.application.dto.response.ParkingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tickets")
public interface TicketController {

    @PostMapping("/calculate")
    @ResponseBody
    ResponseEntity<ParkingResponse> calculate(@RequestBody ParkingRequest request);

}
