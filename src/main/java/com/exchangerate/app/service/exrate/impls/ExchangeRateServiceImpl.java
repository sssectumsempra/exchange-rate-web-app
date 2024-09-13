package com.exchangerate.app.service.exrate.impls;

import com.exchangerate.app.model.Currency;
import com.exchangerate.app.repository.ExchangeRateRepository;
import com.exchangerate.app.service.exrate.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<Currency> getCurrencies() {
        log.info("Fetching currencies from repository...");
        return exchangeRateRepository.findAll();
    }

    @Override
    public Optional<Currency> getCurrencyByCc(String cc) {
        log.info("Fetching currency from repository by cc: {}", cc);
        return exchangeRateRepository.findByCurrencyCode(cc);
    }

    @Override
    public Double getUahByCcAndCurrencyAmount(String cc, double currencyAmount) {
        log.info("Fetching rate of UAH currency by cc: {}, and currency amount: {}", cc, currencyAmount);
        Double rate = exchangeRateRepository.findByCurrencyCode(cc)
                .orElseThrow(() -> new RuntimeException("Currency not found")).getRate();
        log.info("Calculate UAH amount...");
        Double uah = currencyAmount * rate;
        log.info("Return UAH amount...");
        return uah;
    }

    @Override
    public Double getCurrencyAmountByUahAmount(int uahAmount, String cc) {
        log.info("Fetching rate of UAH currency by cc: {}", cc);
        Double rate = exchangeRateRepository.findByCurrencyCode(cc)
                .orElseThrow(()-> new RuntimeException("Currency not found")).getRate();
        log.info("Calculate currency amount doing division...");
        Double currency = uahAmount / rate;
        log.info("Return currency amount...");
        return currency;
    }
}
