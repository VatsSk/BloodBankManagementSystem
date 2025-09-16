package com.example.bloodBank.dtos;

import javax.validation.constraints.*;


import java.time.LocalDateTime;

public class StockDto {
    private Long id;
    private String bloodGroup;
    private Double avaliableUnit= 0.0;
    private LocalDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Double getAvaliableUnit() {
        return avaliableUnit;
    }

    public void setAvaliableUnit(Double avaliableUnit) {
        this.avaliableUnit = avaliableUnit;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
