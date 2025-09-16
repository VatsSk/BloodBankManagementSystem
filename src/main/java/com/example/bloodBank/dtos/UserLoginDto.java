package com.example.bloodBank.dtos;

import javax.validation.constraints.*;


public class UserLoginDto {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{5,20}$",
            message = "Username must contain at least one digit, and must not contain spaces or uppercase letters"
    )
    private String userName;



    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 16, message = "Password length should be between 8 and 16 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&)."
    )
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
