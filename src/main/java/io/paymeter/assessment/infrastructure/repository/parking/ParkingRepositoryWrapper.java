package io.paymeter.assessment.infrastructure.repository.parking;

import io.paymeter.assessment.application.exception.ParkingNotFoundException;
import io.paymeter.assessment.domain.repository.RepositoryWrapper;
import io.paymeter.assessment.infrastructure.dto.ParkingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class ParkingRepositoryWrapper implements RepositoryWrapper<ParkingEntity> {

    private static final Supplier<ParkingNotFoundException> PARKING_NOT_FOUND_EXCEPTION_SUPPLIER =
            () -> new ParkingNotFoundException("Parking not found.");

    private final ParkingRepository parkingRepository;

    @Override
    public ParkingEntity findById(final String key) {
        return parkingRepository.findById(key).orElseThrow(PARKING_NOT_FOUND_EXCEPTION_SUPPLIER);
    }
}
