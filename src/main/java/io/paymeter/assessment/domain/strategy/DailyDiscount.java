package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.utils.DiscountUtils;

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
        DiscountUtils.validateParkingEndDate(hours);
        final long fullDays = (hours + 1) / TWENTY_FOUR;

        parkingCalculation.setDuration(duration.toMinutes());
        parkingCalculation.setPrice(DiscountUtils.calculatePriceWithMinutes(TWENTY_FOUR, fullDays, money, hours,
                DAILY_MAX_PRICE, duration) + money.getCurrency().getCurrencyCode());
    }

}
