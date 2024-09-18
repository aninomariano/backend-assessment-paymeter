package io.paymeter.assessment.infrastructure.repository.parking;

import io.paymeter.assessment.domain.strategy.DailyDiscount;
import io.paymeter.assessment.domain.strategy.HalfDayDiscount;
import io.paymeter.assessment.infrastructure.dto.MoneyEntity;
import io.paymeter.assessment.infrastructure.dto.ParkingEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Currency;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class RepositoryStartup {

    private static final Currency EUR = Currency.getInstance("EUR");

    private final ParkingRepository parkingRepository;

    @PostConstruct
    public void preChargeData() {
        parkingRepository.save(ParkingEntity.builder()
                .parkingId("P000123")
                .money(new MoneyEntity(2, EUR))
                .discounts(Set.of(DailyDiscount.DAILY_DISCOUNT_INSTANCE))
                .build());
        parkingRepository.save(ParkingEntity.builder()
                .parkingId("P000456")
                .money(new MoneyEntity(3, EUR))
                .discounts(Set.of(HalfDayDiscount.HALF_DAY_DISCOUNT_INSTANCE))
                .build());
    }
}
