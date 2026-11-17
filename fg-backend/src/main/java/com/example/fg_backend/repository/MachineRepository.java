package com.example.fg_backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fg_backend.entity.Machine;

@Repository 
public interface MachineRepository extends JpaRepository<Machine,Long> {

}
