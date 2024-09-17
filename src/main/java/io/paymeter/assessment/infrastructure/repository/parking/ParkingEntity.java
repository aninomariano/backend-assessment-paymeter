package io.paymeter.assessment.infrastructure.repository.parking;

import io.paymeter.assessment.domain.strategy.Discount;
import lombok.Data;

import java.util.Currency;
import java.util.Set;

@Data
public class ParkingEntity {

    private final String id;

    private final int pricePerHour;

    private final Currency currency;

    private final Set<Discount> discounts;

    private final String location;

}
