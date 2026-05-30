package com.example.HR_management.usermanagement.dto;

import com.example.HR_management.usermanagement.enums.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "Name is required")
        String name,
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,
        @NotNull(message = "Role is required")
        RoleType role,
        Boolean active
) {
}
