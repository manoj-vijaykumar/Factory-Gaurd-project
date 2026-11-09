package com.example.fg_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/api/qa")
public class QAController {
    @GetMapping("/dashboard")
    public String getDashboard()
    {
        return "Welcome to QA Dashboard!";
    }
    
}
