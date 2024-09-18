package io.paymeter.assessment.application.dto.request;

import lombok.Data;

@Data
public class ParkingRequest {

    private final String parkingId;

    private final String from;

    private final String to;

}
