package com.dbs.exceptions;

public class TransactionNotFoundException extends BusinessException{
    public TransactionNotFoundException(String errorMessage){
        super(errorMessage);
    }
}