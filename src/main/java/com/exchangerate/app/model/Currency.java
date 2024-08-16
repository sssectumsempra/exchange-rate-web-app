package com.exchangerate.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Currency {
    @JsonProperty("r030")
    private int reference;

    @JsonProperty("txt")
    private String name;

    private Double rate;

    @JsonProperty("cc")
    private String currencyCode;

    @JsonProperty("exchangedate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate exchangeDate;
}
