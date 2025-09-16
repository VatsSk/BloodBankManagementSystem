package com.example.bloodBank.services;

import com.example.bloodBank.dtos.UserRequestDto;
import com.example.bloodBank.exceptionHandlers.GlobalException;
import com.example.bloodBank.models.BloodRequestModel;
import com.example.bloodBank.models.UserModel;
import com.example.bloodBank.repository.BloodReqDB;
import com.example.bloodBank.repository.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    private BloodReqDB bloodReqDB;

    @Autowired
    private UserDatabase userDatabase;

    public void requestBlood(UserRequestDto userRequestDto,String userName){

        UserModel userModel= userDatabase.getByUserName(userName);
        if(userRequestDto.getType().equalsIgnoreCase("request")) {
            if(bloodReqDB.checkIfRequestStatusIsPending(userName))
                throw new GlobalException("Previous request status is pending, can't generate new");
            bloodReqDB.saveBloodRequest(
                    userRequestDto.getBloodGroup(),
                    userRequestDto.getQuantity(),
                    LocalDateTime.now(),
                    userName,
                    "Admin",
                    LocalDateTime.now(),
                    "pending",
                    userRequestDto.getType(),
                    userModel.getFirstName() + " " + userModel.getLastName(),
                    userModel.getDob()
            );
        }else{
            int lastDonate=this.noOfDaysOfLastDonate(userName);
            if(bloodReqDB.checkIfDonateStatusIsPending(userName)) {
                throw new GlobalException("Previous donate status is pending, can't generate new");
            }else if(lastDonate<90) {
                throw new GlobalException("Can't donate now try after " + (90 - lastDonate)+" days");
            }
            bloodReqDB.saveBloodRequest(
                    userModel.getBloodGroup(),
                    userRequestDto.getQuantity(),
                    LocalDateTime.now(),
                    userName,
                    "Admin",
                    LocalDateTime.now(),
                    "pending",
                    userRequestDto.getType(),
                    userModel.getFirstName() + " " + userModel.getLastName(),
                    userModel.getDob()
            );
        }
    }

    public List<BloodRequestModel> getHistoryByUser(String userName) {
        return bloodReqDB.getListByCreatedBy(userName);
    }

    public BloodRequestModel getByUsername(String username){
        return bloodReqDB.getBloodRequestModelByUserName(username);
    }

    public int noOfDaysOfLastDonate(String username){
      LocalDateTime lastUpdatedDate = bloodReqDB.getLastUpdatedTimeByTypeStatusAndUsername("donate","Approved",username).orElse(null);
        System.out.println(lastUpdatedDate);
       if(lastUpdatedDate!=null)
            return  LocalDate.now().compareTo(lastUpdatedDate.toLocalDate());
       else
        return 100;
    }

}
