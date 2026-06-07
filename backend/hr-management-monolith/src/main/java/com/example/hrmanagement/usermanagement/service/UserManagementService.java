package com.example.hrmanagement.usermanagement.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hrmanagement.common.exception.BadRequestException;
import com.example.hrmanagement.common.exception.ResourceNotFoundException;
import com.example.hrmanagement.usermanagement.dto.CreateUserRequest;
import com.example.hrmanagement.usermanagement.dto.UpdateUserRequest;
import com.example.hrmanagement.usermanagement.dto.UserResponse;
import com.example.hrmanagement.usermanagement.entity.User;
import com.example.hrmanagement.usermanagement.enums.RoleType;
import com.example.hrmanagement.usermanagement.mapper.UserMapper;
import com.example.hrmanagement.usermanagement.repository.UserRepository;

@Service
public class UserManagementService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagementService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createInternalUser(String name, String email, String rawPassword, RoleType role, Boolean active) {
        String normalizedEmail = normalizeEmail(email);
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new BadRequestException("User with this email already exists");
        }

        User user = User.builder()
                .name(name.trim())
                .email(normalizedEmail)
                .password(passwordEncoder.encode(rawPassword))
                .role(role == null ? RoleType.ROLE_EMPLOYEE : role)
                .active(active == null || active)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        User user = createInternalUser(
                request.name(),
                request.email(),
                request.password(),
                request.role(),
                request.active()
        );
        return UserMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        return UserMapper.toResponse(findUserEntityById(id));
    }

    @Transactional(readOnly = true)
    public UserResponse getCurrentUser(User currentUser) {
        return UserMapper.toResponse(findUserEntityById(currentUser.getId()));
    }

    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = findUserEntityById(id);

        if (request.getName() != null && !request.getName().isBlank()) {
            user.setName(request.getName().trim());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            String normalizedEmail = normalizeEmail(request.getEmail());
            if (!normalizedEmail.equals(user.getEmail()) && userRepository.existsByEmail(normalizedEmail)) {
                throw new BadRequestException("User with this email already exists");
            }
            user.setEmail(normalizedEmail);
        }

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        if (request.getActive() != null) {
            user.setActive(request.getActive());
        }

        return UserMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findUserEntityById(id);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(normalizeEmail(email))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    @Transactional(readOnly = true)
    public User findUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }
}

