package com.dbs.exceptions;

public class ExchangeRateNotFoundException extends BusinessException{
    public ExchangeRateNotFoundException(String errorMessage){
        super(errorMessage);
    }
}