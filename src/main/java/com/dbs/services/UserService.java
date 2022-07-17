package com.dbs.services;

import com.dbs.entities.User;
import com.dbs.exceptions.UserAlreadyExistException;
import com.dbs.exceptions.UserNotFoundException;
import com.dbs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User id %s does not exist", id)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User createUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new UserAlreadyExistException(String.format("User id %s already exist", user.getId()));
        }
        return userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User updateUser(User user, int id) {
        User existingUser = getUser(id);
        existingUser.setAge(user.getAge());
        existingUser.setAddress(user.getAddress());
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
        User existingUser = getUser(id);
        userRepository.delete(existingUser);
    }
}
