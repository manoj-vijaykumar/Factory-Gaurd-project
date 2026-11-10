package com.example.fg_backend.dto;

public class UpdateUserRequest {

    private String role;

    public UpdateUserRequest(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    


}
