package com.exchangerate.app.controller;

import com.exchangerate.app.client.ExchangeRateClientJSON;
import com.exchangerate.app.model.Currency;
import com.exchangerate.app.client.ExchangeRateClientXML;
import com.exchangerate.app.model.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ex-rate"/*"${application.endpoint.controller.root}"*/)
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateClientXML exchangeRateClientXML;
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

    // todo deprecated
    @GetMapping(value = "/list", produces = "application/json")
    public List<Rate> getRateListD() {
        return exchangeRateClientXML.getRateList();
    }

    // todo deprecated
    @GetMapping(value = "/cc/{cc}", produces = "application/json")
    public Rate getRateByCc(@PathVariable("cc") String cc) {
        return exchangeRateClientXML.getRate(cc);
    }

    // todo deprecated
    @GetMapping(value = "/cc/{cc}/{amount}", produces = "application/json")
    public Double getUAHByCCAndAmount(@PathVariable("cc") String cc, @PathVariable("amount") int moneyAmount) {
        return exchangeRateClientXML.getUahByCcAndAmount(cc, moneyAmount);
    }

    // todo deprecated
    @GetMapping(value = "/uah/{uah}/{cc}", produces = "application/json")
    public Double getSomeRateByUah(@PathVariable("uah") int uah, @PathVariable("cc") String currency) {
        return exchangeRateClientXML.getSomeRateByUah(uah, currency);
    }
}
