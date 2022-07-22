package com.dbs.controllers.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private int id;
    private String userName;
    private String password;
    private String name;
}
