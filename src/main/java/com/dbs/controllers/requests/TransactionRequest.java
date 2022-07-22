package com.dbs.controllers.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    private int walletId;
    private int debitId;
    private String debitCurrency;
    private BigDecimal debitAmount;
    private int creditId;
    private String creditCurrency;
    private BigDecimal creditAmount;
    private String description;
    private TransactionType transactionType;
    private BigDecimal amount;
    public enum TransactionType {
        Fund, Withdrawal
    }
}
