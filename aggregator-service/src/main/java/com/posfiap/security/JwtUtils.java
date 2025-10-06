package com.posfiap.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    private static final String SECRET = "b1bH7kG8p9Tq2rS5u7v9x!A%D*F-J@NcRfUjXnZr4u7x!A%D*F-J@NcRfUjXnZr";
    private static final long EXP_MS = 24 * 60 * 60 * 1000;

    private Key key() { return Keys.hmacShaKeyFor(SECRET.getBytes()); }

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of("uid", userId, "role", role))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXP_MS))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
    }
}
