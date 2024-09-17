package io.paymeter.assessment.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class ParkingCalculation {

    private final String parkingId;

    private final LocalDateTime from;

    private final LocalDateTime to;

    private final Money money;

    private final long duration;

    private final String price;
}
