package io.paymeter.assessment.domain.service;

import io.paymeter.assessment.domain.dto.ParkingCalculation;

public interface ParkingService {

    /**
     * Calculates parking price depending on the parking price and discount applied.
     *
     * @param parkingCalculation the parking calculation clear instance
     */
    void calculateParking(ParkingCalculation parkingCalculation);
}
