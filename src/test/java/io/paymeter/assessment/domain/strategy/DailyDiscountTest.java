package io.paymeter.assessment.domain.strategy;

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

@ExtendWith(MockitoExtension.class)
public class DailyDiscountTest {

    private DailyDiscount dailyDiscount;

    @BeforeEach
    void setUp() {
        dailyDiscount = DailyDiscount.DAILY_DISCOUNT_INSTANCE;
    }

    @Test
    void testMethod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse("2024-02-27T09:00:00", formatter);
        LocalDateTime to = LocalDateTime.parse("2024-02-29T09:00:00", formatter);
        final ParkingCalculation parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .money(new Money(2))
                .build();

        dailyDiscount.calculateWithDiscount(parkingCalculation);

        assertThat(parkingCalculation.getPrice()).isNotBlank();
    }
}
