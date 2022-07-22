package com.dbs.exceptions;

public class CurrencyNotFoundException extends BusinessException{
    public CurrencyNotFoundException(String errorMessage){
        super(errorMessage);
    }
}