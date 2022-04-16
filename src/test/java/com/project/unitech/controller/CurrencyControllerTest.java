package com.project.unitech.controller;

import com.project.unitech.dto.CurrencyDto;
import com.project.unitech.service.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CurrencyControllerTest {

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyController currencyController;

    @Test
    void retrieveExchangeValueFeign() {

        CurrencyDto currencyDto = CurrencyDto.builder()
                .conversionMultiple(BigDecimal.valueOf(10))
                .build();

        Mockito.when(currencyService.retrieveExchangeValue(Mockito.anyString(), Mockito.anyString())).thenReturn(currencyDto);

        Assertions.assertDoesNotThrow(() -> currencyController.retrieveExchangeValueFeign("usd", "azn", BigDecimal.valueOf(0.7)));

    }

    @Test
    void retrieveExchangeValueFeign_fail() {

        Mockito.when(currencyService.retrieveExchangeValue(Mockito.anyString(), Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> currencyController.retrieveExchangeValueFeign("usd", "azn", BigDecimal.valueOf(0.7)));

    }


}
