package com.project.unitech.service;

import com.project.unitech.dto.CurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "currency-service", value = "currency", url = "http://localhost:8081/currency")
public interface CurrencyService {

    @PostMapping
    CurrencyDto retrieveExchangeValue(@RequestBody String from, @RequestBody String to);
}
