package com.dbs.controllers.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotEmpty(message = "{userRequest.name.notEmpty}")
    private String name;

    private String userName;

    private String password;
}
