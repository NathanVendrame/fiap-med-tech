package com.posfiap.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    // use uma chave >= 256 bits (32+ chars). Configure pelo application.properties
    private final SecretKey key;
    private final long expirationMillis;

    public JwtUtils(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration:86400000}") long expirationMillis // 24h
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(Long userId, String email, String role) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(email)
                .claim("uid", userId)
                .claim("role", role) // PACIENTE/MEDICO/ENFERMEIRO
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(expirationMillis)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    public Map<String, Object> claims(String token) {
        return parse(token).getBody();
    }
}
