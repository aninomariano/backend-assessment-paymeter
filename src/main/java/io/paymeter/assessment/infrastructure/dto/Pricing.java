package io.paymeter.assessment.infrastructure.dto;

import io.paymeter.assessment.domain.dto.Money;
import io.paymeter.assessment.dto.ParkingLocation;

public record Pricing(int pricePerHour, Money money, ParkingLocation parkingLocation) {}
