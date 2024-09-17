package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.ParkingCalculation;

public interface Discount {

    /**
     * This method executes and decorates the pricing data depending on each location.
     *
     * @param parkingCalculation provided parking info.
     */
    void executeDiscount(ParkingCalculation parkingCalculation);
}
