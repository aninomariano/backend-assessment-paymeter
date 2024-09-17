package io.paymeter.assessment.strategy;

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

    private DailyDiscount ordinoParkingStrategy;

    @BeforeEach
    void setUp() {
        ordinoParkingStrategy = DailyDiscount.INSTANCE;
    }

    @Test
    void testMethod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse("2014-11-25T10:00:20", formatter);
        LocalDateTime to = LocalDateTime.parse("2014-11-26T10:00:00", formatter);
        final ParkingCalculation parkingCalculation = ParkingCalculation.builder()
                .from(from)
                .to(to)
                .build();
        /*
        final var calculation = ordinoParkingStrategy.execute(parking);

        assertThat(calculation.getPrice()).isNotBlank();

         */
    }
}
