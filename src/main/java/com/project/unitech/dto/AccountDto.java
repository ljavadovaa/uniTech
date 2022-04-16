package com.project.unitech.dto;

import com.project.unitech.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String accountName;
    private String accountNumber;
    private BigDecimal accountBalance;
    private CurrencyEnum accountCurrency;
}
