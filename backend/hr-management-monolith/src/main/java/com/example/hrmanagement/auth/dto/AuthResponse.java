package com.example.hrmanagement.auth.dto;

import com.example.hrmanagement.usermanagement.dto.UserResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response containing JWT token and user details")
public record AuthResponse(
        @Schema(example = "eyJhbGciOiJIUzI1NiJ9.sample-token")
        String accessToken,
        @Schema(example = "Bearer")
        String tokenType,
        @Schema(example = "86400000")
        long expiresIn,
        UserResponse user
) {
}

