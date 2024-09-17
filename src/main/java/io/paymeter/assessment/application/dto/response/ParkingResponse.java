package io.paymeter.assessment.application.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
public class ParkingResponse {

    String parkingId;

    String from;

    String to;

    int duration;

    String price;

}
