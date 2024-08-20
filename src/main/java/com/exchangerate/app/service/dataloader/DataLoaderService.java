package com.exchangerate.app.service.dataloader;

import com.exchangerate.app.client.ExchangeRateClientJSON;
import com.exchangerate.app.model.Currency;
import com.exchangerate.app.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataLoaderService {

    @Autowired
    private final ExchangeRateClientJSON exchangeRateClientJSON;

    @Autowired
    private final ExchangeRateRepository exchangeRateRepository;

    @Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE)
    public void loadData() {
        Currency[] currencies = exchangeRateClientJSON.getCurrencies();

        for (Currency currency : currencies) {
            exchangeRateRepository.save(currency);
        }
    }
}
