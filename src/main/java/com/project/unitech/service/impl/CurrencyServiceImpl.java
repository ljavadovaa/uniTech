package com.project.unitech.service.impl;

import com.project.unitech.cache.CurrencyCache;
import com.project.unitech.dto.CurrencyDto;
import com.project.unitech.entity.User;
import com.project.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyCache currencyCache;

    @Override
    public CurrencyDto retrieveExchangeValue(String from, String to) {

        CurrencyDto currencyDto = null;
        if ("USD".equalsIgnoreCase(from)) {
            currencyDto = new CurrencyDto(from, to,
                    BigDecimal.valueOf(73.1), BigDecimal.ZERO, BigDecimal.ZERO);
        } else if ("EUR".equalsIgnoreCase(from)) {
            currencyDto = new CurrencyDto(from, to,
                    BigDecimal.valueOf(85.18), BigDecimal.ZERO, BigDecimal.ZERO);
        } else if ("AUD".equalsIgnoreCase(from)) {
            currencyDto = new CurrencyDto(from, to,
                    BigDecimal.valueOf(52.6), BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return currencyDto;
    }

    public CurrencyDto setAndGetCurrencyDto(User user) {
        CurrencyDto currencyFromCache = currencyCache.getCurrencyFromCache(user.getPin());

        return CurrencyDto.builder()
                .from(currencyFromCache.getFrom())
                .to(currencyFromCache.getTo())
                .quantity(currencyFromCache.getQuantity())
                .calculatedAmount(currencyFromCache.getCalculatedAmount())
                .conversionMultiple(currencyFromCache.getConversionMultiple())
                .build();
    }
}
