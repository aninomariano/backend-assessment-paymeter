package io.paymeter.assessment.domain.dto;

import io.paymeter.assessment.domain.strategy.Discount;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

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

    private Set<Discount> discounts;
}
