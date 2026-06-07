package com.example.hrmanagement.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hrmanagement.common.exception.ResourceNotFoundException;
import com.example.hrmanagement.usermanagement.service.UserManagementService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserManagementService userManagementService;

    public CustomUserDetailsService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userManagementService.loadUserByEmail(username);
        } catch (ResourceNotFoundException exception) {
            throw new UsernameNotFoundException(exception.getMessage(), exception);
        }
    }
}

