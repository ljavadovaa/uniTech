package com.project.unitech.controller;

import com.project.unitech.dto.CurrencyDto;
import com.project.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public CurrencyDto retrieveExchangeValueFeign(@RequestBody String from, @RequestBody String to, @RequestBody BigDecimal quantity) {
        try {
            CurrencyDto response = currencyService.retrieveExchangeValue(from, to);
            return new CurrencyDto(from, to, response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()));

        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }
}
