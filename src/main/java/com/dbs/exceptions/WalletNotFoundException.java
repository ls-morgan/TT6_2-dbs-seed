package com.dbs.exceptions;

public class WalletNotFoundException extends BusinessException{
    public WalletNotFoundException(String errorMessage){
        super(errorMessage);
    }
}