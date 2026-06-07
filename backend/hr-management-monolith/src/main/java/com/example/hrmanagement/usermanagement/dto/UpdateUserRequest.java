package com.example.hrmanagement.usermanagement.dto;

import com.example.hrmanagement.usermanagement.enums.RoleType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Payload for updating a managed user")
@Getter
@Setter
public class UpdateUserRequest {

    @Schema(example = "Priya Singh")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;

    @Schema(example = "priya@example.com")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(example = "priya@123")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Schema(example = "ROLE_MANAGER")
    private RoleType role;

    @Schema(example = "true")
    private Boolean active;
}

