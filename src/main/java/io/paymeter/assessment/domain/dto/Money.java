package io.paymeter.assessment.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.Objects;

@Getter
@Setter
public class Money {

    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

    private final int amount;
    private Currency currency;

    public Money(final int amount) {
        this.amount = amount;
        this.currency = DEFAULT_CURRENCY;
    }

    public String getCurrencyCode() {
        return currency.getCurrencyCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
