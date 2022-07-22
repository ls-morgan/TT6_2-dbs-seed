package com.dbs.services;

import com.dbs.controllers.requests.UserLoginRequest;
import com.dbs.controllers.requests.UserRequest;
import com.dbs.controllers.requests.UserUpdateRequest;
import com.dbs.controllers.responses.UserLoginResponse;
import com.dbs.entities.User;
import com.dbs.exceptions.IncorrectPasswordException;
import com.dbs.exceptions.UserAlreadyExistException;
import com.dbs.exceptions.UserNotFoundException;
import com.dbs.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Optional<User> user = userRepository.findByUserName(userLoginRequest.getUserName());
        if (user.isEmpty()) {
//            throw new UserNotFoundException(String.format("Username %s does not exist in the system", userLoginRequest.getUserName()));
            return UserLoginResponse.builder().status("USER DOES NOT EXIST").build();
        }
        User existingUser = user.get();
        if (!existingUser.getPassword().equals(userLoginRequest.getPassword())) {
            return UserLoginResponse.builder()
                    .name(existingUser.getName())
                    .userName(existingUser.getUserName())
                    .status("INCORRECT PASSWORD")
                    .build();
        }

        return UserLoginResponse.builder()
                .name(existingUser.getName())
                .userName(existingUser.getUserName())
                .status("SUCCESS")
                .build();
    }

    @Transactional(readOnly = true)
    public User getUser(int id) {
        log.info(String.format("Getting user id %s", id));
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User id %s does not exist", id)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User createUser(User user) {
        log.info(String.format("Creating user %s", user.getName()));
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new UserAlreadyExistException(String.format("Username %s already exist", user.getUserName()));
        }
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(UserUpdateRequest userUpdateRequest) {
        Optional<User> user = userRepository.findByUserName(userUpdateRequest.getUserName());
        if(!validatePassword(userUpdateRequest)) {
            throw new IncorrectPasswordException("Incorrect password. Please try again.");
        }
        User existingUser = user.get();
        log.info(String.format("Updating user id %s", userUpdateRequest));
        existingUser.setPassword(userUpdateRequest.getNewPassword());
        existingUser.setUserName(existingUser.getUserName());
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
        log.info(String.format("Deleting user id %s", id));
        User existingUser = getUser(id);
        userRepository.delete(existingUser);
    }


    private UserLoginResponse userToSuUserLoginResponse(User user) {
        return UserLoginResponse.builder()
                .name(user.getName())
                .userName(user.getUserName())
                .status("SUCCESS")
                .build();
    }

    private boolean validatePassword(UserUpdateRequest userUpdateRequest) {
        Optional<User> user = userRepository.findByUserName(userUpdateRequest.getUserName());
        if(user.isEmpty()) return false;
        return user.get().getPassword().equals(userUpdateRequest.getCurrentPassword());
    }
}
