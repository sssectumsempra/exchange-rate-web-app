package com.exchangerate.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Rate {
    @JsonProperty("txt")
    private String name;

    private String rate;

    @JsonProperty("cc")
    private String currencyCode;

    @JsonProperty("exchangedate")
    private String exchangeDate;
}
