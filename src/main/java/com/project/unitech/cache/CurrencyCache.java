package com.project.unitech.cache;

import com.project.unitech.dto.CurrencyDto;

public interface CurrencyCache {

    void saveCurrencyToCache (String userId, CurrencyDto referralDTO);

    CurrencyDto getCurrencyFromCache(String userId);

}
