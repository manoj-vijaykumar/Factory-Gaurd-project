package com.example.fg_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@Table(name="machines")
public class Machine {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String status="ACTIVE";

    @Column(nullable = false)
    private Double tempLimit;

    @Column(nullable = false)
    private Double vibrationLimit;

    
    private LocalDateTime createdAt=LocalDateTime.now();


    public Machine() {
    }

    public Long getId() {
        return id;
    }

    

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTempLimit() {
        return tempLimit;
    }

    public void setTempLimit(Double tempLimit) {
        this.tempLimit = tempLimit;
    }

    public Double getVibrationLimit() {
        return vibrationLimit;
    }

    public void setVibrationLimit(Double vibrationLimit) {
        this.vibrationLimit = vibrationLimit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
    
}
