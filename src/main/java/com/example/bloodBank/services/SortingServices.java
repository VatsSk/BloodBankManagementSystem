package com.example.bloodBank.services;

import com.example.bloodBank.models.BloodRequestModel;
import com.example.bloodBank.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortingServices {
    public List<UserModel> sortByName(List<UserModel> userModelList){
        return userModelList.stream()
                .sorted((u1, u2) -> u1.getFirstName().compareToIgnoreCase(u2.getFirstName()))
                .collect(Collectors.toList());
    }

    public List<UserModel> sortByUserName(List<UserModel> userModelList){
        return userModelList.stream()
                .sorted((u1, u2) -> u1.getUserName().compareToIgnoreCase(u2.getUserName()))
                .collect(Collectors.toList());
    }

    public List<UserModel> sortByDob(List<UserModel> userModelList){
        return userModelList.stream()
                .sorted(Comparator.comparing(UserModel::getDob))
                .collect(Collectors.toList());
    }

    public List<UserModel> sortByCreatedDate(List<UserModel> userModelList){
        return userModelList.stream()
                .sorted(Comparator.comparing(UserModel::getCreatedOn))
                .collect(Collectors.toList());
    }
    public List<BloodRequestModel> sortByRequestDate(List<BloodRequestModel> bloodRequestModelList){
        return bloodRequestModelList.stream()
                .sorted(Comparator.comparing(BloodRequestModel::getCreatedOn))
                .collect(Collectors.toList());
    }
    public List<BloodRequestModel> sortByBloodGroup(List<BloodRequestModel> bloodRequestModelList){
        return bloodRequestModelList.stream()
                .sorted(Comparator.comparing(BloodRequestModel::getBloodGroup))
                .collect(Collectors.toList());
    }
}
