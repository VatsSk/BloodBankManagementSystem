package com.example.bloodBank.services;

import com.example.bloodBank.dtos.ForgotPasswordDto;
import com.example.bloodBank.dtos.UserLoginDto;
import com.example.bloodBank.dtos.UserRegisterDto;
import com.example.bloodBank.exceptionHandlers.GlobalException;
import com.example.bloodBank.exceptionHandlers.InvalidRoleException;
import com.example.bloodBank.exceptionHandlers.UserAlreadyExistsException;
import com.example.bloodBank.exceptionHandlers.UserDoesnotExistsException;
import com.example.bloodBank.models.UserModel;
import com.example.bloodBank.repository.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthServices {
    @Autowired
    private UserDatabase userDatabase;

    public void loginUser(UserLoginDto userLoginDto) {
        String userName = userLoginDto.getUserName();
        UserModel userModel = userDatabase.getByUserName(userName);

        if (userModel == null) {
            throw new UserDoesnotExistsException("User Doesn't Exist");
        }

        if (userModel.isLocked()) {
            throw new GlobalException("User is locked. Can't login now.");
        }
        Long id = userModel.getId();
        if (!userLoginDto.getPassword().equals(userModel.getPassword())) {
            int loginAttempts = userModel.getLoginAttempts() + 1;
            userDatabase.updateLoginAttemptsById(id, loginAttempts);

            if (loginAttempts >= 3) {
                userDatabase.updateIsLockedById(id, true);
                throw new GlobalException("Wrong password. Account locked.");
            }
            int attemptsLeft = 3 - loginAttempts;
            throw new GlobalException("Wrong password. " + attemptsLeft + " attempt(s) left.");
        }

        userDatabase.updateLoginAttemptsById(id, 0);
    }

    public void updatePassword(ForgotPasswordDto forgotPasswordDto){
        String userName=forgotPasswordDto.getUserName();
        if(!userDatabase.existsByUserName(userName)) {
            throw new UserDoesnotExistsException("User Doesn't Exists");
        }else{
            UserModel userModel=userDatabase.getByUserName(userName);
            if(!userModel.getSecurityAnswer1().equalsIgnoreCase(forgotPasswordDto.getSecurityAnswer1())
                    || !userModel.getSecurityAnswer2().equalsIgnoreCase(forgotPasswordDto.getSecurityAnswer2())
                    || !userModel.getSecurityAnswer3().equalsIgnoreCase(forgotPasswordDto.getSecurityAnswer3())){
                throw new GlobalException("Wrong answer of Security Questions") ;
            }
            userDatabase.updatePasswordById(userModel.getId(),forgotPasswordDto.getNewPassword());
            userDatabase.updateLoginAttemptsById(userModel.getId(),0);
            userDatabase.updateIsLockedById(userModel.getId(),false);
        }
    }
    public void verifyExisting(UserRegisterDto userRegisterDto) throws RuntimeException {

        if (!userDatabase.existsByUserName(userRegisterDto.getUserName())) {
            if(userRegisterDto.getSecurityAnswer1().equalsIgnoreCase(userRegisterDto.getSecurityAnswer2())
            || userRegisterDto.getSecurityAnswer1().equalsIgnoreCase(userRegisterDto.getSecurityAnswer3())
            || userRegisterDto.getSecurityAnswer2().equalsIgnoreCase(userRegisterDto.getSecurityAnswer3())){
                throw new GlobalException("Security Answers can't be same");
            }
            if(userDatabase.existsByEmail(userRegisterDto.getEmail())){
                throw new GlobalException("Email already Exists");
            }
            if (userRegisterDto.getRole().equalsIgnoreCase("ENDUSER")) {
                if(LocalDate.now().getYear()-userRegisterDto.getDob().getYear()<20)
                       throw new RuntimeException("Age Should be 20+");
            } else {
                throw new InvalidRoleException("Invalid Role");
            }
        }else
           throw new UserAlreadyExistsException("User Already Exists");
    }

    private void saveUser(UserModel userModel) {
        userDatabase.insertUser(
                userModel.getUserName(),
                userModel.getCreatedBy(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getDob(),
                userModel.getPassword(),
                userModel.getCreatedOn(),
                userModel.getRole(),
                userModel.isLocked(),
                userModel.getLoginAttempts(),
                userModel.getEmail(),
                userModel.getSecurityQuestion1(),
                userModel.getSecurityAnswer1(),
                userModel.getSecurityQuestion2(),
                userModel.getSecurityAnswer2(),
                userModel.getSecurityQuestion3(),
                userModel.getSecurityAnswer3(),
                userModel.getBloodGroup()
        );
    }

    public void signupUser(UserRegisterDto userRegisterDto) {
        UserModel userModel = new UserModel();
        userModel.setUserName(userRegisterDto.getUserName());
        userModel.setDob(userRegisterDto.getDob());
        userModel.setLocked(userRegisterDto.getIsLocked());
        userModel.setCreatedOn(LocalDateTime.now());
        userModel.setCreatedBy("Self");
        userModel.setFirstName(userRegisterDto.getFirstName());
        userModel.setLastName(userRegisterDto.getLastName());
        userModel.setPassword(userRegisterDto.getPassword());
        userModel.setLoginAttempts(0);
        userModel.setRole("ENDUSER");
        userModel.setEmail(userRegisterDto.getEmail());
        userModel.setSecurityQuestion1(userRegisterDto.getSecurityQuestion1());
        userModel.setSecurityAnswer1(userRegisterDto.getSecurityAnswer1());
        userModel.setSecurityQuestion2(userRegisterDto.getSecurityQuestion2());
        userModel.setSecurityAnswer2(userRegisterDto.getSecurityAnswer2());
        userModel.setSecurityQuestion3(userRegisterDto.getSecurityQuestion3());
        userModel.setSecurityAnswer3(userRegisterDto.getSecurityAnswer3());
        userModel.setBloodGroup(userRegisterDto.getBloodGroup());
        saveUser(userModel);
    }

    public List<UserModel> getAllUsers(String status){
        if(status.equals("active")) {
            return userDatabase.getAllUser(false);
        }else if(status.equals("locked")){
            return userDatabase.getAllUser(true);
        }else{
            return userDatabase.getAllUser();
        }
    }

    public UserModel getUserByUsername(String userName){
        return  userDatabase.getByUserName(userName);
    }

    public int countActiveUsers(boolean isLocked){
        return userDatabase.countActiveUsers(isLocked);
    }

    public void updateLastLoginDetail(String username){
        userDatabase.updateLastLoginDetail(username,LocalDateTime.now());
    }

    public String getEmailFromUserName(String userName){
        return userDatabase.getEmailFromUserName(userName);
    }
}
