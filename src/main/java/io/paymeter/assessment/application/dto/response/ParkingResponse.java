package io.paymeter.assessment.application.dto.response;

import lombok.Data;
import lombok.Value;

@Data
public class ParkingResponse {

    String parkingId;

    String from;

    String to;

    int duration;

    String price;

}
