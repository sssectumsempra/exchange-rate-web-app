package com.exchangerate.app.controller;

import com.exchangerate.app.model.Rate;
import com.exchangerate.app.client.ExchangeRateClientXML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ex-rate")
public class ExchangeRateController {

    @GetMapping(value = "/list", produces = "application/json")
    public List<Rate> getRateList() {
        ExchangeRateClientXML exchangeRateClientXML = new ExchangeRateClientXML();

        return exchangeRateClientXML.getRateList();
    }

    @GetMapping(value = "/cc/{cc}")
    public Rate getRateByCc(@PathVariable("cc") String cc) {
        ExchangeRateClientXML exchangeRateClientXML = new ExchangeRateClientXML();

        return exchangeRateClientXML.getRate(cc);
    }
}
