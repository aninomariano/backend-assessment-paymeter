package io.paymeter.assessment.application.dto.response;

import lombok.Data;

@Data
public class ParkingResponse {

    private final String parkingId;

    private final String from;

    private final String to;

    private final int duration;

    private final String price;

}
