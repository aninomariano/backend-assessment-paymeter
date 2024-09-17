package io.paymeter.assessment.domain.strategy;

import io.paymeter.assessment.domain.dto.ParkingCalculation;

public interface Discount {

    /**
     * This method calculates the pricing data depending on each discount strategy.
     *
     * @param parkingCalculation provided parking info.
     */
    void calculateWithDiscount(ParkingCalculation parkingCalculation);
}
