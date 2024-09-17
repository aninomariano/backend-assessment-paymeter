package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.dto.ParkingLocation;
import io.paymeter.assessment.infrastructure.repository.parking.Pricing;

import java.time.Duration;

public enum HalfDayDiscount implements Discount {

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

        final long fullDays = hours / 12;
        final double extraHours = (double) hours / fullDays;
        final double result = extraHours - 12;
        final double finalResult = Math.ceil(result * fullDays);

        /*
        value == 50 / 24 = 2.08

mid == 50 / value first digit

result == mid - 24

final == result x mid

         */

        return (long) finalResult;
    }
}
