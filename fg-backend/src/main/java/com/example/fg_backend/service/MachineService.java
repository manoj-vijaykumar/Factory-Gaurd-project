package com.example.fg_backend.service;

import org.springframework.stereotype.Service;

import com.example.fg_backend.entity.Machine;
import com.example.fg_backend.repository.MachineRepository;
import java.util.*;

@Service
public class MachineService {

    private MachineRepository machineRepo;
    

    public MachineService(MachineRepository machineRepo) {
        this.machineRepo = machineRepo;
    }


    public Machine addMachine(Machine machine)
    {
        return machineRepo.save(machine);
    }

    public Machine updateMachine(Long id,Machine machine)
    {
        Machine current_machine=machineRepo.findById(id).orElseThrow(()->new RuntimeException("MAchine not found"));
        current_machine.setName(machine.getName());
        current_machine.setLocation(machine.getLocation());
        current_machine.setTempLimit(machine.getTempLimit());
        current_machine.setVibrationLimit(machine.getVibrationLimit());
        return machineRepo.save(current_machine);
    }

    public Machine updateStatus(Long id,Machine machine)
    {
        Machine current_machine=machineRepo.findById(id).orElseThrow(()->new RuntimeException());
        current_machine.setStatus("INACTIVE");
        return machineRepo.save(current_machine);
    }

    public List<Machine> getMachines()
    {
        return machineRepo.findAll();
    }

    public void deleteMachines(Long id)
    {
        Machine current_machine=machineRepo.findById(id).orElseThrow(()-> new RuntimeException());
        machineRepo.delete(current_machine);

    }
    

}
