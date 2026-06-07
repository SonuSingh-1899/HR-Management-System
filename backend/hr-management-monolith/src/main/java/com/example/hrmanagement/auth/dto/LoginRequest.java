package com.example.hrmanagement.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Payload for user login")
public record LoginRequest(
        @Schema(example = "rahul@example.com")
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(example = "rahul@123")
        @NotBlank(message = "Password is required")
        String password
) {
}

