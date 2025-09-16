package com.example.bloodBank.dtos;


import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRegisterDto {

    private Long id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{5,20}$",
            message = "Username must contain at least one digit, and must not contain spaces or uppercase letters"
    )
    private String userName;


    @NotEmpty(message = "Blood group can't be empty")
    private String bloodGroup;

    @NotNull
    private String createdBy;

    @NotBlank(message = "  Name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
    private String firstName;

    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
    private String lastName;

    @NotNull
    @Past(message = "DOB can't be in future")
    @DateTimeFormat(pattern ="yyyy-MM-dd" )
    private LocalDate dob;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 16, message = "Password length should be between 8 and 16 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&)."
    )
    private String password;


    private LocalDateTime createdOn;

    private String role = "EndUser";

    private boolean isLocked = false;

    private int loginAttempts = 0;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Invalid email")
    private String email;

    private final String securityQuestion1 = "What is your first school's name?";
    @NotEmpty(message = "Security answer cannot be empty")
    @Pattern(
            regexp = "^(?!\\s*$)(?![\\W_]+$).+",
            message = "Security answer cannot be only special characters or spaces"
    )
    private String securityAnswer1;


    private final String securityQuestion2 = "What was your first pet's name?";
    @NotEmpty(message = "Security answer cannot be empty")
    @Pattern(
            regexp = "^(?!\\s*$)(?![\\W_]+$).+",
            message = "Security answer cannot be only special characters or spaces"
    )
    private String securityAnswer2;

    private final String securityQuestion3 = "What city were you born in?";
    @NotEmpty(message = "Security answer cannot be empty")
    @Pattern(
            regexp = "^(?!\\s*$)(?![\\W_]+$).+",
            message = "Security answer cannot be only special characters or spaces"
    )
    private String securityAnswer3;

    public boolean getIsLocked() {
        return isLocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurityQuestion1() {
        return securityQuestion1;
    }

    public String getSecurityAnswer1() {
        return securityAnswer1;
    }

    public void setSecurityAnswer1(String securityAnswer1) {
        this.securityAnswer1 = securityAnswer1;
    }

    public String getSecurityQuestion2() {
        return securityQuestion2;
    }

    public String getSecurityAnswer2() {
        return securityAnswer2;
    }

    public void setSecurityAnswer2(String securityAnswer2) {
        this.securityAnswer2 = securityAnswer2;
    }

    public String getSecurityQuestion3() {
        return securityQuestion3;
    }

    public String getSecurityAnswer3() {
        return securityAnswer3;
    }

    public void setSecurityAnswer3(String securityAnswer3) {
        this.securityAnswer3 = securityAnswer3;
    }
}
