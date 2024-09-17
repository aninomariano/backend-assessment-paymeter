package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.dto.ParkingLocation;
import io.paymeter.assessment.infrastructure.dto.Pricing;

import java.time.Duration;

public enum DailyDiscount implements Discount {

    DAILY_DISCOUNT_INSTANCE;

    private static final int DAILY_MAX_PRICE = 15;
    private static final int TWENTY_FOUR = 24;

    @Override
    public void calculateWithDiscount(final ParkingCalculation parkingCalculation) {
        final Money money = parkingCalculation.getMoney();
        final Duration duration = Duration.between(parkingCalculation.getFrom(), parkingCalculation.getTo());
        final long hours = duration.toHours();
        final long fullDays = (hours + 1) / TWENTY_FOUR;

        parkingCalculation.setDuration(duration.toMinutes());
        if (fullDays > 1) {
            parkingCalculation.setPrice(calculateTotal(hours, fullDays, money.getAmount()) + money.getCurrency().getCurrencyCode());
        }
    }

    private long calculateTotal(final long hours, final long fullDays, final long hourlyPrice) {
        return (fullDays * DAILY_MAX_PRICE) + (calculateExtraHours(hours, fullDays) * hourlyPrice);
    }

    private long calculateExtraHours(final long hours, final long fullDays) {
        final double extraHours = (double) (hours + 1) / fullDays;
        return (long) Math.ceil((extraHours - TWENTY_FOUR) * fullDays);
    }

}
