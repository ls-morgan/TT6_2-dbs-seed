package com.dbs.controllers.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRateRequest {

    private String baseCurrency;
    private String exchangeCurrency;
    private BigDecimal rate;
}
