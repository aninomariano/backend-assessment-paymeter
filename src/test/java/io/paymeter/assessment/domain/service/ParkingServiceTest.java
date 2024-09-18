package io.paymeter.assessment.domain.service;

import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.strategy.DailyDiscount;
import io.paymeter.assessment.infrastructure.dto.MoneyEntity;
import io.paymeter.assessment.infrastructure.dto.ParkingEntity;
import io.paymeter.assessment.infrastructure.repository.parking.ParkingRepositoryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    private static final String P_01 = "P01";
    private static final String FROM_RANDOM_DATE = "2024-09-14T09:00";
    private static final String TO_RANDOM_DATE = "2024-09-16T09:00";
    private static final int DURATION = 3690;
    private static final String PRICE = "106EUR";
    private static final String EUR = "EUR";

    @Mock
    private ParkingRepositoryWrapper parkingRepositoryWrapper;

    @Mock
    private DateService dateService;

    @Mock
    private DailyDiscount dailyDiscount;

    @InjectMocks
    private ParkingServiceImpl parkingService;

    @Test
    void givenParkingService_whenTryToCalculateParking_thenMustRunAllOk() {
        final var parkingEntity = ParkingEntity.builder()
                .parkingId(P_01)
                .money(new MoneyEntity(2, Currency.getInstance(EUR)))
                .discounts(Set.of(dailyDiscount))
                .build();
        final var parkingCalculation = ParkingCalculation.builder()
                .parkingId(P_01)
                .from(LocalDateTime.parse(FROM_RANDOM_DATE))
                .to(LocalDateTime.parse(TO_RANDOM_DATE))
                .build();

        when(parkingRepositoryWrapper.findById(P_01)).thenReturn(parkingEntity);

        parkingService.calculateParking(parkingCalculation);

        assertThat(parkingCalculation.getDiscounts()).isNotEmpty();
        assertThat(parkingCalculation.getMoney().getAmount()).isEqualTo(2);
        verify(dateService, never()).getInstant();
        verify(dailyDiscount, only()).calculateWithDiscount(parkingCalculation);
    }

    @Test
    void givenParkingService_whenTryToCalculateParkingWithoutMaxDate_thenMustRunAllOk() {
        final var parkingEntity = ParkingEntity.builder()
                .parkingId(P_01)
                .money(new MoneyEntity(2, Currency.getInstance(EUR)))
                .discounts(Set.of(dailyDiscount))
                .build();
        final var parkingCalculation = ParkingCalculation.builder()
                .parkingId(P_01)
                .from(LocalDateTime.parse(FROM_RANDOM_DATE))
                .build();

        when(parkingRepositoryWrapper.findById(P_01)).thenReturn(parkingEntity);

        parkingService.calculateParking(parkingCalculation);

        assertThat(parkingCalculation.getDiscounts()).isNotEmpty();
        assertThat(parkingCalculation.getMoney().getAmount()).isEqualTo(2);
        verify(dateService, only()).getInstant();
        verify(dailyDiscount, only()).calculateWithDiscount(parkingCalculation);
    }
}
