package io.paymeter.assessment.infrastructure.dto;

import io.paymeter.assessment.domain.strategy.Discount;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.Set;

@Value
@Builder
@KeySpace("parking")
public class ParkingEntity {

    @Id
    String parkingId;

    MoneyEntity money;

    Set<Discount> discounts;

}
