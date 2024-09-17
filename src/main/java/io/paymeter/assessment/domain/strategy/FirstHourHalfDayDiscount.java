package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.dto.ParkingLocation;
import io.paymeter.assessment.infrastructure.repository.parking.Pricing;

import java.time.Duration;

public enum FirstHourHalfDayDiscount implements Discount {

    INSTANCE;

    @Override
    public void executeDiscount(final ParkingCalculation parkingCalculation) {
        final var pricing = new Pricing(2, new Money(2), ParkingLocation.ORDINO);
        final long hours = Duration.between(parkingCalculation.getFrom(), parkingCalculation.getTo()).toHours();
        final long discount = calculateDiscount(hours, pricing.pricePerHour());

        return Calculation.builder()
                .price(pricing.money().getCurrency().getSymbol() + pricing.pricePerHour())
                .duration(hours)
                .build();
    }

    private long calculateDiscount(final long hours, final int pricePerHour) {
        //TODO decorate an object not return the value
        if (hours < 12) {
            return (hours * pricePerHour) - 2;
        }
        return 1L;
    }
}
