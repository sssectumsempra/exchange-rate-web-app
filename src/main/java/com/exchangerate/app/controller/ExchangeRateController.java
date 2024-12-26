package com.exchangerate.app.controller;

import com.exchangerate.app.model.Currency;
import com.exchangerate.app.service.exrate.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ex-rate"/*"${application.endpoint.controller.root}"*/)
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    //Exceptions handling in controller are inappropriate
    @GetMapping(value = "/currencies", produces = "application/json")
    public ResponseEntity<List<Currency>> getCurrencies() {
        log.info("Received request to get currencies list");
        return ResponseEntity.ok(exchangeRateService.getCurrencies());
    }

    //What is cc? You should write the code in a way another engineer will understand
    //What the reason of returning Optional inside ResponseEntity?
    @GetMapping(value = "/currencies/cc/{cc}", produces = "application/json")
    public ResponseEntity<Currency> getCurrencyByCc(@PathVariable("cc") String cc) {
        log.info("Received request to get currency by cc: {}", cc);
        return ResponseEntity.ok(exchangeRateService.getCurrencyByCc(cc));
    }


    //What is CC?
    @GetMapping(value = "/currencies/cc/{cc}/amount/{amount}")
    public ResponseEntity<Double> getUAHByCcAndCurrencyAmount(@PathVariable("cc") String cc,
                                                              @PathVariable("amount") double currencyAmount) {
        log.info("Received request to get UAH currency by cc: {}, and amount: {}", cc, currencyAmount);
        return ResponseEntity.ok(exchangeRateService.getUahByCcAndCurrencyAmount(cc, currencyAmount));
    }

    //What is CC? Other engineer should easy read and understand naming you use
    @GetMapping(value = "/currencies/uah_amount/{amount}/cc/{cc}")
    public ResponseEntity<Double> getCurrencyAmountByUAHAmount(@PathVariable("amount") int uahAmount,
                                                               @PathVariable("cc") String cc) {
        log.info("Received request to get currency amount by UAH amount: {}, currency: {}", uahAmount, cc);
        return ResponseEntity.ok(exchangeRateService.getCurrencyAmountByUahAmount(uahAmount, cc));
    }
}
