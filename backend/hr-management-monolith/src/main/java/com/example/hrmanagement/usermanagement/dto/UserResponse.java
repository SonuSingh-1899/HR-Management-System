package com.example.hrmanagement.usermanagement.dto;

import java.time.LocalDateTime;

import com.example.hrmanagement.usermanagement.enums.RoleType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User response payload")
public record UserResponse(
        @Schema(example = "1")
        Long id,
        @Schema(example = "Rahul Sharma")
        String name,
        @Schema(example = "rahul@example.com")
        String email,
        @Schema(example = "ROLE_EMPLOYEE")
        RoleType role,
        @Schema(example = "true")
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

