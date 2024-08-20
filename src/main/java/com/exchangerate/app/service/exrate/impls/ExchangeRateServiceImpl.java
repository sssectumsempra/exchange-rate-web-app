package com.exchangerate.app.service.exrate.impls;

import com.exchangerate.app.model.Currency;
import com.exchangerate.app.repository.ExchangeRateRepository;
import com.exchangerate.app.service.exrate.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<Currency> getCurrencies() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public Optional<Currency> getCurrencyByCc(String cc) {
        return exchangeRateRepository.findByCurrencyCode(cc);
    }

    @Override
    public Double getUahByCcAndCurrencyAmount(String cc, double currencyAmount) {
        Double rate = exchangeRateRepository.findByCurrencyCode(cc)
                .orElseThrow(() -> new RuntimeException("Currency not found")).getRate();
        Double uah = currencyAmount * rate;
        return uah;
    }

    @Override
    public Double getCurrencyAmountByUahAmount(int uahAmount, String cc) {
        Double rate = exchangeRateRepository.findByCurrencyCode(cc)
                .orElseThrow(()-> new RuntimeException("Currency not found")).getRate();
        Double currency = uahAmount / rate;
        return currency;
    }
}
