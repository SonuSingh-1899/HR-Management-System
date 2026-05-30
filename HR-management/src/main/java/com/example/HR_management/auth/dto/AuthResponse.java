package com.example.HR_management.auth.dto;

import com.example.HR_management.usermanagement.dto.UserResponse;

public record AuthResponse(
        String accessToken,
        String tokenType,
        long expiresIn,
        UserResponse user
) {
}
