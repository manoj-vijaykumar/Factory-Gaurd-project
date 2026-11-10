package com.example.fg_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fg_backend.dto.UpdateUserRequest;
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

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id)
    {
        User user = userService.getUserById(id);
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole().name()
        );
    }

    @PutMapping("/{id}/role")
    public UpdateUserRequest updateUser(@PathVariable Long id,@RequestBody User user)
    {
        User updated_user=userService.updateUser(id, user);
        return new UpdateUserRequest(
            updated_user.getRole().name()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return "User Deleted Successfully";
    }

}
