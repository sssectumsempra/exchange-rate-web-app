package com.exchangerate.app.service.dataloader;

import com.exchangerate.app.client.ExchangeRateClient;
import com.exchangerate.app.model.Currency;
import com.exchangerate.app.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
//Naming, what kind of data? What if we have different data?
// Why if we load data we persist it?
public class DataLoaderService {

    //Do not use redundant annotations
    private final ExchangeRateClient exchangeRateClient;
    private final ExchangeRateRepository exchangeRateRepository;

    @Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE) //Any reason to do Fixed delay MAX_VALUE
    public void loadData() {
        List<Currency> currencies = Arrays.stream(exchangeRateClient.getCurrencies())
                .collect(Collectors.toList());

        log.info("Saving currencies to array...");
        //any loop operation with database are harmful, you should always find a way to persist all in 1 insert
        exchangeRateRepository.saveAll(currencies);
    }
}
