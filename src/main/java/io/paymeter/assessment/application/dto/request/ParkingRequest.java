package io.paymeter.assessment.application.dto.request;

import lombok.Value;

@Value
public class ParkingRequest {

    String parkingId;

    String from;

    String to;

}
