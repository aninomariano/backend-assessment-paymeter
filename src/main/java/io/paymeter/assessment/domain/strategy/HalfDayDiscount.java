package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.utils.DiscountUtils;

import java.time.Duration;

public enum HalfDayDiscount implements Discount {

    HALF_DAY_DISCOUNT_INSTANCE;

    private static final int HALF_DAY_MAX_PRICE = 20;
    private static final int TWELVE = 12;

    @Override
    public void calculateWithDiscount(final ParkingCalculation parkingCalculation) {
        final Money money = parkingCalculation.getMoney();
        final Duration duration = Duration.     between(parkingCalculation.getFrom(), parkingCalculation.getTo());
        final long hours = duration.toHours();
        DiscountUtils.validateParkingEndDate(hours);
        final long fullDays = hours / TWELVE;

        if (hours > 12) {
            parkingCalculation.setPrice(DiscountUtils.calculatePriceWithMinutes(TWELVE, fullDays, money, hours,
                    HALF_DAY_MAX_PRICE, duration) + money.getCurrency().getCurrencyCode());
        } else if (hours == 0) {
            parkingCalculation.setPrice((0 + money.getCurrency().getCurrencyCode()));
        } else if (hours == 1){
            parkingCalculation.setPrice((hours * money.getAmount()) + money.getCurrency().getCurrencyCode());
        } else {
            parkingCalculation.setPrice((DiscountUtils.calculatePriceWithMinutes(TWELVE, fullDays, money, hours,
                    HALF_DAY_MAX_PRICE, duration) - money.getAmount()) + money.getCurrency().getCurrencyCode());
        }
        parkingCalculation.setDuration(duration.toMinutes());
    }
}
