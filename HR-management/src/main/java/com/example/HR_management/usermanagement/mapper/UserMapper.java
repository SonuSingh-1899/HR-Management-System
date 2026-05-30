package com.example.HR_management.usermanagement.mapper;

import com.example.HR_management.usermanagement.dto.UserResponse;
import com.example.HR_management.usermanagement.entity.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
