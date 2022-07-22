package com.dbs.controllers.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {

    private int id;
    private String userName;
    private String name;
    private String status;
}
