package com.react_springboot.service;

import com.react_springboot.entity.User;
import com.react_springboot.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public   User createUser(User user){
      return   userRepo.save(user);
    }
}
