package com.example.HR_management.usermanagement.dto;

import java.time.LocalDateTime;

import com.example.HR_management.usermanagement.enums.RoleType;

public record UserResponse(
        Long id,
        String name,
        String email,
        RoleType role,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
