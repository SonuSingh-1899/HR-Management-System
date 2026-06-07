package com.example.hrmanagement.usermanagement.mapper;

import com.example.hrmanagement.usermanagement.dto.UserResponse;
import com.example.hrmanagement.usermanagement.entity.User;

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

