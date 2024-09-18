package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.application.exception.InvalidDateException;
import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.strategy.DailyDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class DailyDiscountTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private DailyDiscount dailyDiscount;

    @BeforeEach
    void setUp() {
        dailyDiscount = DailyDiscount.DAILY_DISCOUNT_INSTANCE;
    }

    @Test
    void givenDailyDiscountStrategy_whenTryToCalculateWithDiscountForTwoDays_thenMustCalculateOk() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-29T09:00:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(2))
                .build();

        dailyDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("30EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(2880L);
    }

    @Test
    void givenDailyDiscountStrategy_whenTryToCalculateWithDiscountForTwoDaysAndSomeHours_thenMustCalculateOk() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-29T12:00:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(2))
                .build();

        dailyDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("36EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(3060L);
    }

    @Test
    void givenDailyDiscountStrategy_whenTryToCalculateWithDiscountForHalfDay_thenMustCalculateOk() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T20:00:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(2))
                .build();

        dailyDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("22EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(660L);
    }

    @Test
    void givenDailyDiscountStrategy_whenTryToCalculateWithDiscountForHalfHour_thenMustIncrementOneHourValue() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T09:30:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(2))
                .build();

        dailyDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("2EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(30L);
    }

    @Test
    void givenDailyDiscountStrategy_whenTryToCalculateWithDiscountForLessThanAMinute_thenMustReturnZero() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T09:00:50", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(2))
                .build();

        dailyDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("0EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(0L);
    }

    @Test
    void givenDailyDiscountStrategy_whenTryToCalculateWithDiscountForLessThanFourHours_thenMustReturnZero() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T11:20:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(2))
                .build();

        dailyDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("6EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(140L);
    }

    @Test
    void givenDailyDiscountStrategyWithInvalidEndDate_whenTryToCalculateWithDiscount_thenMustThrowAnError() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-25T09:00:50", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(2))
                .build();

        assertThatThrownBy(() -> dailyDiscount.calculateWithDiscount(parkingCalculation))
                .isInstanceOf(InvalidDateException.class);
    }
}
