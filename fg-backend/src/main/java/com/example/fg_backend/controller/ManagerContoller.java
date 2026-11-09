package com.example.fg_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fg_backend.dto.UserResponse;
import com.example.fg_backend.entity.User;
import com.example.fg_backend.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/api/manager")
public class ManagerContoller {
    private UserService userService;
    
    public ManagerContoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getDashboard()
    {
        return "Welcome to Manager Dashboard!";
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers()
    {
        List<User> reg_users=userService.getUsers();
        return reg_users.stream().map(a-> new UserResponse(
            a.getId(),
            a.getName(),
            a.getEmail(),a.getRole().name())).toList();
    }
    
}
