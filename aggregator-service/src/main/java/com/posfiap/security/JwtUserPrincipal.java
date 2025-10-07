package com.posfiap.security;

public record JwtUserPrincipal(Long id, String email, String role) {}
