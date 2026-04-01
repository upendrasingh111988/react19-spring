package com.react_springboot.controller;

import com.react_springboot.entity.User;
import com.react_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){

        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> usersFromDb = userService.getAllUsers();
        return ResponseEntity.ok(usersFromDb);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable Integer userId){
        User userById = userService.getUserById(userId);
        return ResponseEntity.ok(userById);
    }
    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user , @PathVariable Integer userId){
        return userService.updateUser(user,userId);
    }
}
