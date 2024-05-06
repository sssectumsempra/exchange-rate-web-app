package com.exchangerate.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rate {
    @JsonProperty("txt")
    String txt;

    @JsonProperty("rate")
    String rate;

    @JsonProperty("cc")
    String cc;

    @JsonProperty("exchangedate")
    String date;
}
