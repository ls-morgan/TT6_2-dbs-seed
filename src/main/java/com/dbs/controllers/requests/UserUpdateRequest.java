package com.dbs.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserUpdateRequest {

    @NotEmpty(message = "{userRequest.name.notEmpty}")
    private String name;

    private String userName;
    private String currentPassword;
    private String newPassword;
}
