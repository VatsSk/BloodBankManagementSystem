package com.example.bloodBank.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table
public class BloodStockModel {
    @NotEmpty
    private String bloodGroup;
    @NotNull
    private Double availableUnits;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime lastUpdatedInfo;

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Double getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(Double availableUnits) {
        this.availableUnits = availableUnits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdatedInfo() {
        return lastUpdatedInfo;
    }

    public void setLastUpdatedInfo(LocalDateTime lastUpdatedInfo) {
        this.lastUpdatedInfo = lastUpdatedInfo;
    }
}
