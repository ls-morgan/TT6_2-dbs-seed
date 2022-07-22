package com.dbs.controllers.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyRequest {

    private int walletId;
    private String currency;
    private BigDecimal amount;
}
