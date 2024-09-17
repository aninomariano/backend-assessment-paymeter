package io.paymeter.assessment.infrastructure.repository.parking;

import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.dto.ParkingLocation;

public record Pricing(int pricePerHour, Money money, ParkingLocation parkingLocation) {}
