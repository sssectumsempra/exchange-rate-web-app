package com.exchangerate.app.service.exrate;

import com.exchangerate.app.model.Currency;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateService {
    List<Currency> getCurrencies();

    Currency getCurrencyByCc(String cc);

    Double getUahByCcAndCurrencyAmount(String cc, double currencyAmount);

    Double getCurrencyAmountByUahAmount(int uahAmount, String cc);
}
