package com.example.HR_management.auth.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.HR_management.auth.entity.User;
import com.example.HR_management.auth.repository.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public String saveUser(User user){
        if (userRepo.existsByEmail(user.getEmail())) {
            return "User already exist";
        }

        String encodedpassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedpassword);
        
        userRepo.save(user);

        return "User created successfully";
    }
}
