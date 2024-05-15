package com.exchangerate.app.client;

import com.exchangerate.app.model.Rate;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeRateClientXML {
    private static final String XML_EXCHANGE_RATE_URL = "{application.endpoint.client.root}";

    public List<Rate> getRateList() {
        NodeList currencyList = getDocument().getElementsByTagName("currency");

        List<Rate> rates = new ArrayList<>();

        for (int i = 0; i < currencyList.getLength(); i++) {
            Node currencyNode = currencyList.item(i);
            if (currencyNode.getNodeType() == Node.ELEMENT_NODE) {
                Element currencyElement = (Element) currencyNode;
                String exTxt = currencyElement.getElementsByTagName("txt").item(0).getTextContent().trim();
                String exRate = currencyElement.getElementsByTagName("rate").item(0).getTextContent().trim();
                String exCC = currencyElement.getElementsByTagName("cc").item(0).getTextContent().trim();
                String exDate = currencyElement.getElementsByTagName("exchangedate").item(0).getTextContent().trim();
                rates.add(new Rate(exTxt, exRate, exCC, exDate));
            }
        }

        return rates;
    }

    public Rate getRate(String cc) {
        NodeList currencyList = getDocument().getElementsByTagName("currency");

        Rate rate = null;

        for (int i = 0; i < currencyList.getLength(); i++) {
            Node currencyNode = currencyList.item(i);
            if (currencyNode.getNodeType() == Node.ELEMENT_NODE) {
                Element currencyElement = (Element) currencyNode;
                String exTxt = currencyElement.getElementsByTagName("txt").item(0).getTextContent().trim();
                String exRate = currencyElement.getElementsByTagName("rate").item(0).getTextContent().trim();
                String exCC = currencyElement.getElementsByTagName("cc").item(0).getTextContent().trim();
                String exDate = currencyElement.getElementsByTagName("exchangedate").item(0).getTextContent().trim();
                if (exCC.equals(cc)) {
                    rate = new Rate(exTxt, exRate, exCC, exDate);
                    break;
                }
            }
        }

        return rate;
    }

    private Document getDocument() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new URL(XML_EXCHANGE_RATE_URL).openStream());
            document.getDocumentElement().normalize();

            return document;
        } catch (ParserConfigurationException | SAXException | MalformedURLException e) {
            throw new RuntimeException("Couldn't get Document instance ", e);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't open stream from parsed document", e);
        }
    }
}
