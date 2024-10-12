package com.ijse.posproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.posproject.entity.User;
import com.ijse.posproject.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        try {
            User newuser = new User();
            newuser.setUserName(user.getUserName());
            newuser.setPassword(user.getPassword());
            User createUser = userService.createUser(newuser); 
            return ResponseEntity.status(201).body(createUser);      
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }     
        
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
            List<User> users = userService.getAllUsers();
            return ResponseEntity.status(201).body(users);
    }

    

}
