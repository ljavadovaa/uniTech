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
public class BalanceRestrictionCheckDto {

    private SenderInfoDto senderInfoDTO;

    private BigDecimal transferAmount;

    private CurrencyEnum transferCurrency;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SenderInfoDto {

        private BigDecimal senderBalance;

        private CurrencyEnum senderCurrency;
    }
}
