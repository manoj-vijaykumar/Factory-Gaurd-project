package com.example.fg_backend.controller;

import com.example.fg_backend.repository.MachineRepository;
import com.example.fg_backend.service.MachineService;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fg_backend.dto.StatusRequest;
import com.example.fg_backend.dto.UpdateUserRequest;
import com.example.fg_backend.dto.UserResponse;
import com.example.fg_backend.entity.Machine;
import com.example.fg_backend.entity.User;
import com.example.fg_backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController 
@RequestMapping("/api/manager")
public class ManagerContoller {
    // private final MachineService machineService_1;
    private final MachineRepository machineRepo;
    private UserService userService;
    private MachineService machineService;
    
    public ManagerContoller(UserService userService, MachineRepository machineRepo, MachineService machineService) {
        this.userService = userService;
        this.machineRepo = machineRepo;
        this.machineService=machineService;
    }

    @GetMapping("/dashboard")
    public String getDashboard()
    {
        return "Welcome to Manager Dashboard!";
    }

    // Get all the users 
    @GetMapping("/users")
    public List<UserResponse> getUsers()
    {
        List<User> reg_users=userService.getUsers();
        return reg_users.stream().map(a-> new UserResponse(
            a.getId(),
            a.getName(),
            a.getEmail(),a.getRole().name())).toList();
    }

    // Get user by Id
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

    // Upadate the user name and email address
    @PutMapping("/{id}/updatedetails")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated_user=userService.updateUser(id,user);
        return new UserResponse(
            updated_user.getId(),
             updated_user.getName(),
             updated_user.getEmail(),
             updated_user.getRole().name());

    }

    @PutMapping("/{id}/role")
    public UpdateUserRequest updateUserRole(@PathVariable Long id,@RequestBody User user)
    {
        User updated_user=userService.updateUserRole(id, user);
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

    @PatchMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable Long id)
    {
        userService.deactivateUser(id);
        return "User deactivated successfully";
    }


    @PatchMapping("/{id}/activate")
    public String activateUser(@PathVariable Long id)
    {
        userService.activateUser(id);
            return "User activated";
    }


    @PostMapping("/machines")
    public Machine addMachine(@RequestBody Machine machine) {

        Machine added_machine= machineService.addMachine(machine);
        return machineRepo.save(added_machine);        
    }

    @PutMapping("{id}/machines")
    public Machine updateMachine(@PathVariable Long id, @RequestBody Machine machine)
    {
        Machine updated_machine=machineService.updateMachine(id, machine);
        return machineRepo.save(updated_machine);
    }

    @PutMapping("{id}/status")
    public StatusRequest updateStatus(@PathVariable Long id,@RequestBody Machine machine)
    {
        Machine updated_status=machineService.updateStatus(id,machine);
        return new StatusRequest(updated_status.getStatus());
    }

    @GetMapping("/machines")
    public List<Machine> getMachines()
    {
        return machineService.getMachines();
    }

    @DeleteMapping("/{id}/removeMachine")
    public String deleteMachines(@PathVariable Long id)
    {
        machineService.deleteMachines(id);
        return "Deleted successfully";
    }
    
    


}
