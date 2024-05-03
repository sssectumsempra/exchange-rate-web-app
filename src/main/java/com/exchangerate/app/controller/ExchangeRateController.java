package com.exchangerate.app.controller;

import com.exchangerate.app.model.Rate;
import com.exchangerate.app.client.ExchangeRateClientXML;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ex-rate")
public class ExchangeRateController {

    @GetMapping(value = "/rate", produces = "application/json")
    public List<Rate> getRate() throws IOException, SAXException, ParserConfigurationException {

        return ExchangeRateClientXML.getRate();
    }
}
