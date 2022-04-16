package com.project.unitech.service;

import com.project.unitech.cache.CurrencyCache;
import com.project.unitech.dto.CurrencyDto;
import com.project.unitech.entity.User;
import com.project.unitech.service.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyCache currencyCache;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @ParameterizedTest
    @ValueSource(strings = {"USD","EUR", "AUD"})
    void retrieveExchangeValue(String from) {

        Assertions.assertDoesNotThrow(() -> currencyService.retrieveExchangeValue(from, "AZN"));

    }

    @Test
    void setAndGetCurrencyDto() {

        User user = User.builder()
                .pin("AAA1111")
                .build();

        CurrencyDto currencyDto = CurrencyDto.builder()
                .conversionMultiple(BigDecimal.valueOf(10))
                .calculatedAmount(BigDecimal.valueOf(10))
                .quantity(BigDecimal.valueOf(10))
                .from("from")
                .to("to")
                .build();

        Mockito.when(currencyCache.getCurrencyFromCache(Mockito.anyString())).thenReturn(currencyDto);

        Assertions.assertDoesNotThrow(() -> currencyService.setAndGetCurrencyDto(user));

    }

}
