package com.exchangerate.app.client;

import com.exchangerate.app.model.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateClient { //Do we have some other not *JSON clients?
    private static final String JSON_EXCHANGE_RATE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static Currency[] currencies = null; //Why we store this in class filed?
    private static Currency currency = null;  //Same

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public Currency[] getCurrencies() {
        if (getCurrenciesArray() == null) {
            log.error("currencies array is empty");
            throw new RuntimeException(); // What kind of exception should be here? Current exception too wide
        }
        return getCurrenciesArray();
    }

    // You shouldn't leave unused code
    public Currency getCurrencyByCc(String cc) {
        if (getCurrenciesArray() == null) {
            log.error("currencies array is empty");
            throw new RuntimeException();
        }

        currencies = getCurrenciesArray();

        for (int i = 0; i < currencies.length; i++) {
            String currencyCode = currencies[i].getCurrencyCode();
            if (cc.equals(currencyCode)) {
                currency = currencies[i];
            }
        }

        return currency;
    }

    // You shouldn't leave unused code
    public Double getUAHbyCcAndCurrencyAmount(String cc, double currencyAmount) {
        if (getCurrenciesArray() == null) {
            log.error("currencies array is empty");
            throw new RuntimeException();
        }

        currencies = getCurrenciesArray();

        for (int i = 0; i < currencies.length; i++) {
            String currencyCode = currencies[i].getCurrencyCode();
            if (cc.equals(currencyCode)) {
                currencyAmount = currencies[i].getRate() * currencyAmount;
            }
        }

        return currencyAmount;
    }

    // You shouldn't leave unused code
    public Double getCurrencyAmountByUAHAmount(int uahAmount, String cc) {
        currencies = getCurrenciesArray();

        double amount = 0.0;

        for (int i = 0; i < currencies.length; i++) {
            String currencyCode = currencies[i].getCurrencyCode();
            if (cc.equals(currencyCode)) {
                amount = uahAmount / currencies[i].getRate();
            }
        }

        return amount;
    }

    private Currency[] getCurrenciesArray() {
        log.info("Getting http request by URL...");
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(JSON_EXCHANGE_RATE_URL)).GET().build();

        // No need to log every operation, how it can help?
        //HttpCline can be build 1 time and injected

        HttpResponse<String> httpResponse;

        try {
            log.info("Sending http request...");
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String jsonValueToString = httpResponse.body();

            //Object mapper can be created once

            currencies = objectMapper.readValue(jsonValueToString.getBytes(), Currency[].class);
        } catch (IOException | InterruptedException e) {
            log.error("Exception occurred while sending http request {}", e.getMessage());
            //if you do logging it should help you in investigation
        }

        return currencies;
    }
}
