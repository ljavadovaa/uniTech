package com.project.unitech.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CurrencyEnum {
    AZN(1),
    USD(2),
    EUR(3);

    private final Integer currencyDbCode;

    CurrencyEnum(int currencyDbCode) {
        this.currencyDbCode = currencyDbCode;
    }

    public static CurrencyEnum findByName(String currency) {
        return Arrays.stream(CurrencyEnum.values())
                .filter(e -> e.name().equalsIgnoreCase(currency))
                .findFirst().orElse(null);
    }
}
