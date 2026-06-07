package com.example.employee_service.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public record JwtAuthenticatedUser(
        String email,
        Collection<? extends GrantedAuthority> authorities
) {
}
