package com.example.hrmanagement.usermanagement.dto;

import com.example.hrmanagement.usermanagement.enums.RoleType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload for creating a managed user")
public record CreateUserRequest(
        @Schema(example = "Priya Singh")
        @NotBlank(message = "Name is required")
        String name,
        @Schema(example = "priya@example.com")
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(example = "priya@123")
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,
        @Schema(example = "ROLE_HR")
        @NotNull(message = "Role is required")
        RoleType role,
        @Schema(example = "true")
        Boolean active
) {
}

