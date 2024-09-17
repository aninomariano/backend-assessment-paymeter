package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.dto.Money;

import java.time.Duration;

public enum FirstHourHalfDayDiscount implements Discount {

    FIRST_HOUR_HALF_DAY_DISCOUNT_INSTANCE;

    @Override
    public void calculateWithDiscount(final ParkingCalculation parkingCalculation) {
        final Money money = parkingCalculation.getMoney();
        final Duration duration = Duration.between(parkingCalculation.getFrom(), parkingCalculation.getTo());
        final long hours = duration.toHours() + 1;
        if (hours < 12) {
            parkingCalculation.setDuration(duration.toMinutes());
            parkingCalculation.setPrice((hours * money.getAmount()) - money.getAmount() + money.getCurrency().getCurrencyCode());
        }
    }
}
