package com.example.HR_management.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HR_management.auth.entity.User;

public interface UserRepo extends JpaRepository<User, Long> { 
    User findByEmail(String Email);
    boolean existsByEmail(String email);
}
