package com.example.HR_management.usermanagement.controller;

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

import com.example.HR_management.usermanagement.dto.CreateUserRequest;
import com.example.HR_management.usermanagement.dto.UpdateUserRequest;
import com.example.HR_management.usermanagement.dto.UserResponse;
import com.example.HR_management.usermanagement.entity.User;
import com.example.HR_management.usermanagement.service.UserManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UserManagementController {

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(userManagementService.getCurrentUser(currentUser));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userManagementService.getUserById(id));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userManagementService.createUser(request));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.ok(userManagementService.updateUser(id, request));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userManagementService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
