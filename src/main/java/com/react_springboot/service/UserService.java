package com.react_springboot.service;

import com.react_springboot.entity.User;
import com.react_springboot.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public   User createUser(User user){
      return   userRepo.save(user);
    }
    public List<User> getAllUsers(){
       return   userRepo.findAll();
    }
    public User getUserById(Integer userId){
        return userRepo.findById(userId).orElse(null);
    }
    public User updateUser(User user , Integer userId){
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setAge(user.getAge());
        return userRepo.save(existingUser);
    }
}
