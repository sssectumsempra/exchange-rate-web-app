package com.exchangerate.app.service.dataloader;

import com.exchangerate.app.client.ExchangeRateClientJSON;
import com.exchangerate.app.model.Currency;
import com.exchangerate.app.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataLoaderService {

    @Autowired
    private final ExchangeRateClientJSON exchangeRateClientJSON;

    @Autowired
    private final ExchangeRateRepository exchangeRateRepository;

    @Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE)
    public void loadData() {
        log.info("Getting currencies array...");
        Currency[] currencies = exchangeRateClientJSON.getCurrencies();

        log.info("Saving currencies to array...");
        for (Currency currency : currencies) {
            exchangeRateRepository.save(currency);
        }
    }
}
