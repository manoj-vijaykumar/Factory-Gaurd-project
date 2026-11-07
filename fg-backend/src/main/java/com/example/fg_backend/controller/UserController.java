package com.example.fg_backend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fg_backend.dto.LoginRequest;
import com.example.fg_backend.dto.LoginResponse;
import com.example.fg_backend.dto.UserResponse;
import com.example.fg_backend.entity.User;
import com.example.fg_backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController 
@RequestMapping("/api/users")
public class UserController {

    

    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse LoginUser(@RequestBody LoginRequest request)
    {
        return userService.LoginUser(request);
    }
    
}
