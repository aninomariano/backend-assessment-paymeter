package io.paymeter.assessment.domain.service;

import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.domain.strategy.Discount;
import io.paymeter.assessment.infrastructure.dto.ParkingEntity;
import io.paymeter.assessment.infrastructure.repository.parking.ParkingRepositoryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepositoryWrapper parkingRepositoryWrapper;

    private final DateService dateService;

    @Override
    public void calculateParking(final ParkingCalculation parkingCalculation) {
        populateParkingCalculation(parkingCalculation, parkingRepositoryWrapper.findById(parkingCalculation.getParkingId()));
        setupRecentIfEmpty(parkingCalculation);
        final Set<Discount> discountSet = parkingCalculation.getDiscounts();
        discountSet.forEach(discount -> discount.calculateWithDiscount(parkingCalculation));
    }

    private void populateParkingCalculation(final ParkingCalculation parkingCalculation, final ParkingEntity parkingEntity) {
        parkingCalculation.setDiscounts(parkingEntity.getDiscounts());
        parkingCalculation.setMoney(new Money(parkingEntity.getMoney().getAmount()));
    }

    private void setupRecentIfEmpty(final ParkingCalculation parkingCalculation) {
        if (Objects.isNull(parkingCalculation.getTo())) {
            parkingCalculation.setTo(dateService.getInstant());
        }
    }

}
