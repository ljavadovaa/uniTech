package com.project.unitech.cache;

import com.project.unitech.dto.CurrencyDto;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MINUTES;

@Component
@Slf4j
public class CurrencyCacheImpl implements CurrencyCache {
    private final RMapCache<String, CurrencyDto> currencyCache;

    public CurrencyCacheImpl(RedissonClient redissonClient) {
        this.currencyCache = redissonClient.getMapCache("currency");
    }

    @Override
    public void saveCurrencyToCache(String userId, CurrencyDto referralDTO) {
        currencyCache.put(userId, referralDTO, 1, MINUTES);
    }

    @Override
    public CurrencyDto getCurrencyFromCache(String userId) {
        return currencyCache.get(userId);
    }
}
