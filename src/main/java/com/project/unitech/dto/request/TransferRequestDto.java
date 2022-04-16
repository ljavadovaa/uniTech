package com.project.unitech.dto.request;

import com.project.unitech.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDto<S, R> {

    private CurrencyEnum currency;

    @Positive
    private BigDecimal amount;

    @Valid
    private S sender;

    @Valid
    private R receiver;

}
