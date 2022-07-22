package com.dbs.controllers.requests;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String userName;
    private String password;

}
