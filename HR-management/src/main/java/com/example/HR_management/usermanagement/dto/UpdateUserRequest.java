package com.example.HR_management.usermanagement.dto;

import com.example.HR_management.usermanagement.enums.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private RoleType role;

    private Boolean active;
}
