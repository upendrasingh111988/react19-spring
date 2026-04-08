package com.react_springboot.controller;

import com.react_springboot.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        // 🔥 Replace with DB validation
        if ("admin".equals(username) && "admin123".equals(password)) {

            String token = jwtUtil.generateToken(username, "ADMIN");

            return Map.of("token", token);
        }

        throw new RuntimeException("Invalid credentials");
    }
}