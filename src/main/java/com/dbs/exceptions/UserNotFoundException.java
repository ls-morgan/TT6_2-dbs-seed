package com.dbs.exceptions;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
