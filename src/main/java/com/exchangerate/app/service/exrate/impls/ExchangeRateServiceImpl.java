package com.exchangerate.app.service.exrate.impls;

import com.exchangerate.app.exception.CurrencyNotFoundException;
import com.exchangerate.app.model.Currency;
import com.exchangerate.app.repository.ExchangeRateRepository;
import com.exchangerate.app.service.exrate.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {
    //We do not Autowire fields, all engineers knows it's injectable bean
    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<Currency> getCurrencies() {
        List<Currency> currencies = exchangeRateRepository.findAll();
        if (currencies.isEmpty()) {
            throw new CurrencyNotFoundException("No currencies found");
        }
        return currencies;
    }

    @Override
    public Currency getCurrencyByCc(String cc) {
        return exchangeRateRepository.findByCurrencyCode(cc)
                .orElseThrow(() -> {
                    CurrencyNotFoundException e = new CurrencyNotFoundException("Currency not found");
                    log.error("Currency not found by cc: {}", cc, e);
                    return e;
                });
    }

    //Too much logging, it's harmful
    @Override
    public Double getUahByCcAndCurrencyAmount(String cc, double currencyAmount) {
        Double rate = exchangeRateRepository.findByCurrencyCode(cc)
                .orElseThrow(() -> {
                    CurrencyNotFoundException e = new CurrencyNotFoundException("Currency not found");
                    log.error("UAH currency not by cc: {}, and amount: {}", cc, currencyAmount, e);
                    return e;
                }).getRate();
        return currencyAmount * rate;
    }

    //Too much logging
    @Override
    public Double getCurrencyAmountByUahAmount(int uahAmount, String cc) {
        Double rate = exchangeRateRepository.findByCurrencyCode(cc)
                .orElseThrow(() -> {
                    CurrencyNotFoundException e = new CurrencyNotFoundException("Currency not found");
                    log.error("Currency amount not got by UAH amount: {}, and that currency: {}", uahAmount, cc, e);
                    return e;
                }).getRate();
        return uahAmount / rate;
    }
}
