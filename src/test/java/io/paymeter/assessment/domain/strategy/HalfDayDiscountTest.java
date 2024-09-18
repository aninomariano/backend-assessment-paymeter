package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.application.exception.InvalidDateException;
import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class HalfDayDiscountTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private HalfDayDiscount halfDayDiscount;

    @BeforeEach
    void setUp() {
        halfDayDiscount = HalfDayDiscount.HALF_DAY_DISCOUNT_INSTANCE;
    }

    @Test
    void givenHalfDayDiscountStrategy_whenTryToCalculateWithDiscountForTwoDays_thenMustCalculateOk() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-29T09:00:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        halfDayDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("80EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(2880L);
    }

    @Test
    void givenHalfDayDiscountStrategy_whenTryToCalculateWithDiscountForTwoDaysAndSomeHours_thenMustCalculateOk() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-29T12:00:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        halfDayDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("89EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(3060L);
    }

    @Test
    void givenHalfDayDiscountStrategy_whenTryToCalculateWithDiscountForHalfDay_thenMustCalculateOk() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T20:00:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        halfDayDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("30EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(660L);
    }

    @Test
    void givenHalfDayDiscountStrategy_whenTryToCalculateWithDiscountForLessThanAMinute_thenMustReturnZero() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T09:20:50", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        halfDayDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("0EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(20L);
    }

    @Test
    void givenHalfDayDiscountStrategy_whenTryToCalculateWithDiscountForLessThanAnHour_thenMustReturnZero() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T09:20:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        halfDayDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("0EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(20L);
    }

    @Test
    void givenHalfDayDiscountStrategy_whenTryToCalculateWithDiscountForLessThanTwoHours_thenMustReturnZero() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T10:20:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        halfDayDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("3EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(80L);
    }

    @Test
    void givenHalfDayDiscountStrategy_whenTryToCalculateWithDiscountForLessThanFourHours_thenMustReturnZero() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-27T11:20:00", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        halfDayDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isEqualTo("6EUR");
        assertThat(parkingCalculation.getDuration()).isEqualTo(140L);
    }

    @Test
    void givenHalfDayDiscountStrategyWithInvalidEndDate_whenTryToCalculateWithDiscount_thenMustThrowAnError() {
        final var from = LocalDateTime.parse("2024-02-27T09:00:00", DATE_TIME_FORMATTER);
        final var to = LocalDateTime.parse("2024-02-25T09:00:50", DATE_TIME_FORMATTER);
        final var parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        assertThatThrownBy(() -> halfDayDiscount.calculateWithDiscount(parkingCalculation))
                .isInstanceOf(InvalidDateException.class);
    }
}
