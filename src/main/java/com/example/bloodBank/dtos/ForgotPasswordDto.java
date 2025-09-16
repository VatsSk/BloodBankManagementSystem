package com.example.bloodBank.dtos;


import javax.validation.constraints.*;



public class ForgotPasswordDto {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{5,20}$",
            message = "Username must contain at least  one digit, and must not contain spaces or uppercase letters"
    )
    private String userName;



    // Predefined Security Questions
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

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 16, message = "Password length should be between 8 and 16 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&)."
    )
    private String newPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

