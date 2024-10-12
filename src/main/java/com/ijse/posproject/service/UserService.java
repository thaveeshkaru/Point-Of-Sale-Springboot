package com.ijse.posproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.posproject.entity.User;

@Service
public interface UserService {

    User createUser(User user);
    List<User> getAllUsers();
}
