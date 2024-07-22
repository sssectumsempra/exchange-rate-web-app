package com.exchangerate.app.controller;

import com.exchangerate.app.model.Rate;
import com.exchangerate.app.client.ExchangeRateClientXML;
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

    @GetMapping(value = "/list", produces = "application/json")
    public List<Rate> getRateList() {
        return exchangeRateClientXML.getRateList();
    }

    @GetMapping(value = "/cc/{cc}", produces = "application/json")
    public Rate getRateByCc(@PathVariable("cc") String cc) {
        return exchangeRateClientXML.getRate(cc);
    }

    @GetMapping(value = "/cc/{cc}/{amount}", produces = "application/json")
    public Double getUAHByCCAndAmount(@PathVariable("cc") String cc, @PathVariable("amount") int moneyAmount) {
        return exchangeRateClientXML.getUahByCcAndAmount(cc, moneyAmount);
    }

    @GetMapping(value = "/uah/{uah}/{cc}", produces = "application/json")
    public Double getSomeRateByUah(@PathVariable("uah") int uah, @PathVariable("cc") String currency) {
        return exchangeRateClientXML.getSomeRateByUah(uah, currency);
    }
}
