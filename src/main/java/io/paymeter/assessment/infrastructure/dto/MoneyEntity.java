package io.paymeter.assessment.infrastructure.dto;

import lombok.Getter;
import lombok.Value;

import java.util.Currency;

@Value
@Getter
public class MoneyEntity {

    int amount;

    Currency currency;
}
