package com.example.bloodBank.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table
public class BloodRequestModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotEmpty
        private String bloodGroup;
        @NotEmpty
        private String type;   //  request/donate
        @NotNull
        private Double quantity;
        @NotNull
        private LocalDateTime createdOn;
//        @NotEmpty
        private String createdBy;

        private String updatedBy;
        @NotNull
        private LocalDateTime updatedTime;
        private String status;
        private String name;
        private LocalDateTime dob;

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

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public Double getQuantity() {
                return quantity;
        }

        public void setQuantity(Double quantity) {
                this.quantity = quantity;
        }

        public LocalDateTime getCreatedOn() {
                return createdOn;
        }

        public void setCreatedOn(LocalDateTime createdOn) {
                this.createdOn = createdOn;
        }

        public String getCreatedBy() {
                return createdBy;
        }

        public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
        }

        public String getUpdatedBy() {
                return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
                this.updatedBy = updatedBy;
        }

        public LocalDateTime getUpdatedTime() {
                return updatedTime;
        }

        public void setUpdatedTime(LocalDateTime updatedTime) {
                this.updatedTime = updatedTime;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public LocalDateTime getDob() {
                return dob;
        }

        public void setDob(LocalDateTime dob) {
                this.dob = dob;
        }
}
