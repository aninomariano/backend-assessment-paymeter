package io.paymeter.assessment.infrastructure.dto;

import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.strategy.Discount;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.Currency;
import java.util.Set;

@Data
@Builder
@KeySpace("employees")
public class ParkingEntity {

    @Id
    private String parkingId;

    private Money money;

    private Set<Discount> discounts;

    private String location;

}
