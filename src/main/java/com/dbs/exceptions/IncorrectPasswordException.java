package com.dbs.exceptions;

public class IncorrectPasswordException extends BusinessException{
    public IncorrectPasswordException(String errorMessage){
        super(errorMessage);
    }
}