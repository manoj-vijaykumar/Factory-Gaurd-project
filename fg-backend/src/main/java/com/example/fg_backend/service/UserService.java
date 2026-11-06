package com.example.fg_backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fg_backend.dto.LoginRequest;
import com.example.fg_backend.dto.UserResponse;
import com.example.fg_backend.entity.User;
import com.example.fg_backend.repository.UserRepository;

@Service 
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(User user)
    {
        if(userRepository.existsByEmail(user.getEmail()))
        {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser= userRepository.save(user);

        return new UserResponse(savedUser.getId(),
    savedUser.getName(), savedUser.getEmail(),savedUser.getRole().name());
    }

    public UserResponse LoginUser(LoginRequest request)
    {
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("Error"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
            throw new RuntimeException("Invalid password");
        }

        if(!user.isActive())
        {
            throw new RuntimeException("Account is inactive");
        }

        return new UserResponse(user.getId(),
         user.getName(),
         user.getEmail(),
         user.getRole().name());
    }
    
    
}
