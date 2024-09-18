package io.paymeter.assessment.domain.utils;

import io.paymeter.assessment.application.exception.InvalidDateException;
import io.paymeter.assessment.domain.dto.Money;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscountUtils {

    private static final Supplier<InvalidDateException> INVALID_DATE_EXCEPTION_SUPPLIER =
            () -> new InvalidDateException("The parking end date is not correct.");

    public static long calculatePriceWithMinutes(final int parkingPortion, final long fullDays, final Money money,
                                                 final long hours, final int maxTimePrice, final Duration duration) {
        long price;
        if (fullDays > 0) {
            price = calculateTotal(maxTimePrice, hours, fullDays, money.getAmount(), parkingPortion, duration);
        } else if (calculateExtraMinutes(duration) > 0){
            price = (hours * money.getAmount()) + money.getAmount();
        } else {
            price = hours * money.getAmount();
        }
        return price;
    }

    private static long calculateTotal(final int maxTimePrice, final long hours, final long fullDays, final long hourlyPrice,
                                final int parkingPortion, final Duration duration) {
        return ((fullDays * maxTimePrice) + (calculateExtraHours(hours, fullDays, parkingPortion) * hourlyPrice))
                + (calculateExtraMinutes(duration) > 0 ? hourlyPrice : 0);
    }

    private static long calculateExtraHours(final long hours, final long fullDays, final int parkingPortion) {
        final double extraHours = (double) (hours) / fullDays;
        return (long) Math.ceil((extraHours - parkingPortion) * fullDays);
    }

    private static long calculateExtraMinutes(final Duration duration) {
        return duration.toMinutes() - (duration.toHours() * 60);
    }

    public static void validateParkingEndDate(final long hours) {
        if (hours < 0) {
            throw INVALID_DATE_EXCEPTION_SUPPLIER.get();
        }
    }
}
