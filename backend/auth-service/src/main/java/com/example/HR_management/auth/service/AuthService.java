package com.example.HR_management.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HR_management.auth.dto.AuthResponse;
import com.example.HR_management.auth.dto.LoginRequest;
import com.example.HR_management.auth.dto.RegisterRequest;
import com.example.HR_management.security.JwtService;
import com.example.HR_management.usermanagement.entity.User;
import com.example.HR_management.usermanagement.enums.RoleType;
import com.example.HR_management.usermanagement.mapper.UserMapper;
import com.example.HR_management.usermanagement.service.UserManagementService;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserManagementService userManagementService;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authenticationManager,
            UserManagementService userManagementService,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userManagementService = userManagementService;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        User user = userManagementService.createInternalUser(
                request.name(),
                request.email(),
                request.password(),
                RoleType.ROLE_EMPLOYEE,
                true
        );
        return buildAuthResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        String normalizedEmail = userManagementService.normalizeEmail(request.email());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(normalizedEmail, request.password())
        );
        User user = (User) authentication.getPrincipal();
        return buildAuthResponse(user);
    }

    private AuthResponse buildAuthResponse(User user) {
        String accessToken = jwtService.generateToken(user);
        return new AuthResponse(
                accessToken,
                "Bearer",
                jwtService.getJwtExpirationMs(),
                UserMapper.toResponse(user)
        );
    }
}
