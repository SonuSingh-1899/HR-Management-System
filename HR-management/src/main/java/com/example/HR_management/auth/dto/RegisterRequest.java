package com.example.HR_management.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload for registering a new user")
public record RegisterRequest(
        @Schema(example = "Rahul Sharma")
        @NotBlank(message = "Name is required")
        String name,
        @Schema(example = "rahul@example.com")
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(example = "rahul@123")
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) {
}
 
