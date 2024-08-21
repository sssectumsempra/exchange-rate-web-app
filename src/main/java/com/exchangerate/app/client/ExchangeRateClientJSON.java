package com.exchangerate.app.client;

import com.exchangerate.app.model.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class ExchangeRateClientJSON {
    private static final String JSON_EXCHANGE_RATE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static Currency[] currencies = null;
    private static Currency currency = null;

    public Currency[] getCurrencies() {
        if (getCurrenciesArray() == null) {
            log.error("currencies array is empty");
            throw new RuntimeException();
        }

        return getCurrenciesArray();
    }

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
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(JSON_EXCHANGE_RATE_URL)).GET().build();
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse;

        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String jsonValueToString = httpResponse.body();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            currencies = objectMapper.readValue(jsonValueToString.getBytes(), Currency[].class);

        } catch (IOException | InterruptedException e) {
            log.error("Exception occured {}", e.getMessage());
        }

        return currencies;
    }
}
