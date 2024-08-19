package com.exchangerate.app.controller;

import com.exchangerate.app.client.ExchangeRateClientJSON;
import com.exchangerate.app.model.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/ex-rate"/*"${application.endpoint.controller.root}"*/)
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateClientJSON exchangeRateClientJSON;

    @GetMapping(value = "/currencies", produces = "application/json")
    public Currency[] getCurrencies() {
        return exchangeRateClientJSON.getCurrencies();
    }

    @GetMapping(value = "/currencies/cc/{cc}", produces = "application/json")
    public Currency getCurrencyByCc(@PathVariable("cc") String cc) {
        return exchangeRateClientJSON.getCurrencyByCc(cc);
    }

    @GetMapping(value = "/currencies/cc/{cc}/amount/{amount}")
    public Double getUAHByCcAnoCurrencyAmount (@PathVariable("cc") String cc,
                                               @PathVariable("amount") double currencyAmount) {
        return exchangeRateClientJSON.getUAHbyCcAndCurrencyAmount(cc, currencyAmount);
    }

    @GetMapping(value = "/currencies/uah_amount/{amount}/cc/{cc}")
    public Double getCurrencyAmountByUAHAmount(@PathVariable("amount") int uahAmount,
                                               @PathVariable("cc") String cc) {
        return exchangeRateClientJSON.getCurrencyAmountByUAHAmount(uahAmount, cc);
    }
}
