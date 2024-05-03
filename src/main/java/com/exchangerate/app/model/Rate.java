package com.exchangerate.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rate {

    @JsonProperty("txt")
    String txt;

    @JsonProperty("rate")
    String rate;

    @JsonProperty("exchangedate")
    String date;

    public Rate(String txt, String rate, String date) {
        this.txt = txt;
        this.rate = rate;
        this.date = date;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
