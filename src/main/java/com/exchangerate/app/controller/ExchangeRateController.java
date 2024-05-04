package com.exchangerate.app.controller;

import com.exchangerate.app.model.Rate;
import com.exchangerate.app.client.ExchangeRateClientXML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.websocket.server.PathParam;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ex-rate")
public class ExchangeRateController {

    @GetMapping(value = "/list", produces = "application/json")
    public List<Rate> getRateList() throws IOException, SAXException, ParserConfigurationException {
        return ExchangeRateClientXML.getRateList();
    }

    @GetMapping(value = "/cc/{cc}")
    public Rate getRateByCc(@PathParam("cc") String cc) throws IOException, SAXException, ParserConfigurationException {
        return ExchangeRateClientXML.getRate(cc);
    }
}
