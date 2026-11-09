package com.example.fg_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/manager")
public class ManagerContoller {
    @GetMapping("/dashboard")
    public String getDashboard()
    {
        return "Welcome to Manager Dashboard!";
    }
}
