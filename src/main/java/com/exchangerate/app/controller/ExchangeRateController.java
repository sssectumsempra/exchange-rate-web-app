package com.exchangerate.app.controller;

import com.exchangerate.app.exception.CurrencyException;
import com.exchangerate.app.model.Currency;
import com.exchangerate.app.service.exrate.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/ex-rate"/*"${application.endpoint.controller.root}"*/)
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateController {
    @Autowired
    private final ExchangeRateService exchangeRateService;

    @GetMapping(value = "/currencies", produces = "application/json")
    public ResponseEntity<List<Currency>> getCurrencies() {
        log.info("Received request to get currencies list");
        try {
            return ResponseEntity.ok(exchangeRateService.getCurrencies());
        }catch (CurrencyException e) {
            log.error("Currencies list not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/currencies/cc/{cc}", produces = "application/json")
    public ResponseEntity<Optional<Currency>> getCurrencyByCc(@PathVariable("cc") String cc) {
        log.info("Received request to get currency by cc: {}", cc);
        try {
            return ResponseEntity.ok(exchangeRateService.getCurrencyByCc(cc));
        }catch (CurrencyException e) {
            log.error("Currency not found by cc: {}", cc, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/currencies/cc/{cc}/amount/{amount}")
    public ResponseEntity<Double> getUAHByCcAndCurrencyAmount(@PathVariable("cc") String cc,
                                              @PathVariable("amount") double currencyAmount) {
        log.info("Received request to get UAH currency by cc: {}, and amount: {}", cc, currencyAmount);
        try {
            return ResponseEntity.ok(exchangeRateService.getUahByCcAndCurrencyAmount(cc, currencyAmount));
        }catch (CurrencyException e) {
            log.error("UAH currency not by cc: {}, and amount: {}", cc, currencyAmount, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/currencies/uah_amount/{amount}/cc/{cc}")
    public ResponseEntity<Double> getCurrencyAmountByUAHAmount(@PathVariable("amount") int uahAmount,
                                               @PathVariable("cc") String cc) {
        log.info("Received request to get currency amount by UAH amount: {}, currency: {}", uahAmount, cc);
        try {
            return ResponseEntity.ok(exchangeRateService.getCurrencyAmountByUahAmount(uahAmount, cc));
        } catch (CurrencyException e) {
            log.error("Currency amount not got by UAH amount: {}, and that currency: {}", uahAmount, cc, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
