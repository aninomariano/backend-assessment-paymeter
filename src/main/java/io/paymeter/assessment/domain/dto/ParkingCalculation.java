package io.paymeter.assessment.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingCalculation {

    private String parkingId;

    private LocalDateTime from;

    private LocalDateTime to;

    private Money money;

    private long duration;

    private String price;
}
