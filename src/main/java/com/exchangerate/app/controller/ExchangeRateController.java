package com.exchangerate.app.controller;

import com.exchangerate.app.model.Currency;
import com.exchangerate.app.service.exrate.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/ex-rate"/*"${application.endpoint.controller.root}"*/)
@RequiredArgsConstructor
public class ExchangeRateController {
    @Autowired
    private final ExchangeRateService exchangeRateService;

    @GetMapping(value = "/currencies", produces = "application/json")
    public List<Currency> getCurrencies() {
        return exchangeRateService.getCurrencies();
    }

    @GetMapping(value = "/currencies/cc/{cc}", produces = "application/json")
    public Optional<Currency> getCurrencyByCc(@PathVariable("cc") String cc) {
        return exchangeRateService.getCurrencyByCc(cc);
    }

    @GetMapping(value = "/currencies/cc/{cc}/amount/{amount}")
    public Double getUAHByCcAnoCurrencyAmount(@PathVariable("cc") String cc,
                                              @PathVariable("amount") double currencyAmount) {
        return exchangeRateService.getUahByCcAndCurrencyAmount(cc, currencyAmount);
    }

    @GetMapping(value = "/currencies/uah_amount/{amount}/cc/{cc}")
    public Double getCurrencyAmountByUAHAmount(@PathVariable("amount") int uahAmount,
                                               @PathVariable("cc") String cc) {
        return exchangeRateService.getCurrencyAmountByUahAmount(uahAmount, cc);
    }
}
