package io.paymeter.assessment.domain.service;

import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.infrastructure.repository.parking.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;

    @Override
    public void calculateParking(final ParkingCalculation parkingCalculation) {
        parkingRepository.findById(parkingCalculation.getParkingId());
    }

}
