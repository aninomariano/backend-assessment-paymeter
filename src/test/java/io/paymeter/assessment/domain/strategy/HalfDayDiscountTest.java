package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.strategy.DailyDiscount;
import io.paymeter.assessment.domain.strategy.HalfDayDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class HalfDayDiscountTest {

    private HalfDayDiscount halfDayDiscount;

    @BeforeEach
    void setUp() {
        halfDayDiscount = HalfDayDiscount.HALF_DAY_DISCOUNT_INSTANCE;
    }

    @Test
    void testMethod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse("2014-11-25T10:00:20", formatter);
        LocalDateTime to = LocalDateTime.parse("2014-11-26T09:00:00", formatter);
        final ParkingCalculation parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(3))
                .build();

        halfDayDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isNotBlank();


    }
}
