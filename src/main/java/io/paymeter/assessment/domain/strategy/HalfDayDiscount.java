package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.dto.ParkingLocation;
import io.paymeter.assessment.infrastructure.dto.Pricing;

import java.time.Duration;

public enum HalfDayDiscount implements Discount {

    HALF_DAY_DISCOUNT_INSTANCE;

    private static final int HALF_DAY_MAX_PRICE = 15;
    private static final int TWELVE = 12;

    @Override
    public void calculateWithDiscount(final ParkingCalculation parkingCalculation) {
        final Money money = parkingCalculation.getMoney();
        final Duration duration = Duration.between(parkingCalculation.getFrom(), parkingCalculation.getTo());
        final long hours = duration.toHours();
        final long halfDays = (hours + 1) / TWELVE;

        parkingCalculation.setDuration(duration.toMinutes());
        parkingCalculation.setPrice(calculateTotal(hours, halfDays, money.getAmount()) + money.getCurrency().getCurrencyCode());
    }

    private long calculateTotal(final long hours, final long halfDays, final long hourlyPrice) {
        return (halfDays * HALF_DAY_MAX_PRICE) + (calculateExtraHours(hours, halfDays) * hourlyPrice);
    }

    private long calculateExtraHours(final long hours, final long fullDays) {
        final double extraHours = (double) (hours + 1) / fullDays;
        return (long) Math.ceil((extraHours - TWELVE) * fullDays);
    }
}
