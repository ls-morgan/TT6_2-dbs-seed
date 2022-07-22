package com.dbs.controllers;

import com.dbs.controllers.requests.UserLoginRequest;
import com.dbs.controllers.requests.UserRequest;
import com.dbs.controllers.requests.UserUpdateRequest;
import com.dbs.controllers.responses.UserLoginResponse;
import com.dbs.controllers.responses.UserResponse;
import com.dbs.entities.User;
import com.dbs.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(userService.login(userLoginRequest));
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users"),
            @ApiResponse(responseCode = "404", description = "Not found - Users were not found")
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getUsers().stream().map(
                this::userToUserResponse).collect(Collectors.toList()));
    }

    @Operation(summary = "Get specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "404", description = "Not found - User was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userToUserResponse(
                userService.getUser(id)));
    }

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created user")
    })
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userToUserResponse(
                userService.createUser(userRequestToUser(userRequest))));
    }

    @Operation(summary = "Update specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user")
    })
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userToUserResponse(userService.updateUser(userUpdateRequest)));
    }

    @Operation(summary = "Delete specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted user")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("success");
    }

    private User userRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .userName(userRequest.getUserName())
                .password(userRequest.getPassword())
                .build();
    }

    private UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }

}
