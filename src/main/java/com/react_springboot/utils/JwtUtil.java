package com.react_springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ STRONG KEY (>= 256 bits)
    private final SecretKey key = Keys.hmacShaKeyFor(
            "my-super-secret-key-that-is-at-least-32-characters!!".getBytes()
    );

    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                // ✅ FIX: 24 HOURS TOKEN
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256) // ✅ FIXED
                .compact();
    }

    public Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserName(String token) {
        return extractClaims(token).getSubject();
    }

//    public String getRole(String token) {
//
//        return extractClaims(token).get("role", String.class);
//    }

   /* public String getRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }*/

    public String getRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
}