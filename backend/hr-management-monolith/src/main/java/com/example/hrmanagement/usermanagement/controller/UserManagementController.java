package com.example.hrmanagement.usermanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrmanagement.usermanagement.dto.CreateUserRequest;
import com.example.hrmanagement.usermanagement.dto.UpdateUserRequest;
import com.example.hrmanagement.usermanagement.dto.UserResponse;
import com.example.hrmanagement.usermanagement.entity.User;
import com.example.hrmanagement.usermanagement.service.UserManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
@Tag(name = "User Management", description = "Protected APIs for managing users")
@SecurityRequirement(name = "bearerAuth")
public class UserManagementController {

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Returns the profile of the currently authenticated user")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(userManagementService.getCurrentUser(currentUser));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping
    @Operation(summary = "Get all users", description = "Returns all users. Accessible to HR and Manager roles")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Returns a single user by id. Accessible to HR and Manager roles")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userManagementService.getUserById(id));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @PostMapping
    @Operation(summary = "Create user", description = "Creates a new user. Accessible to HR and Manager roles")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userManagementService.createUser(request));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates an existing user. Accessible to HR and Manager roles")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.ok(userManagementService.updateUser(id, request));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes a user by id. Accessible to HR and Manager roles")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userManagementService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

