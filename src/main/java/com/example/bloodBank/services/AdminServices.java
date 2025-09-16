package com.example.bloodBank.services;

import com.example.bloodBank.dtos.BloodReportDTO;
import com.example.bloodBank.exceptionHandlers.GlobalException;
import com.example.bloodBank.models.BloodRequestModel;
import com.example.bloodBank.models.BloodStockModel;
import com.example.bloodBank.repository.BloodReqDB;
import com.example.bloodBank.repository.BloodStockDB;
import com.example.bloodBank.repository.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServices {
    @Autowired
    private BloodReqDB bloodReqDB;

    @Autowired
    private BloodStockDB bloodStockDB;

    @Autowired
    private UserDatabase userDatabase;

    public List<BloodRequestModel> getUserList(){
        return bloodReqDB.getAllRequests();
    }
    public List<BloodRequestModel> getRequestListByStatus(){
        return bloodReqDB.getListByRequestStatus("pending");
    }

    public void updateRequestStatus(Long requestId,String status){
        bloodReqDB.updateReqStatus(requestId,status);
    }

    public void updateIntoBloodStockDB(String bloodGroup,Double quantity,String type){
        if(!bloodStockDB.existsByBloodGroup(bloodGroup)){
            insertDefaultBloodGroups();
        }
        if(type.equalsIgnoreCase("donate")) {
            bloodStockDB.updateDonateStock(bloodGroup, quantity, LocalDateTime.now());
        }else{
           if(this.getAvailableUnitByBloodGroup(bloodGroup)>=quantity) {
                       bloodStockDB.updateRequestStock(bloodGroup, quantity, LocalDateTime.now());
           }else
               throw new GlobalException("Insufficient in stock , Can't approve");
        }
    }

    public List<BloodStockModel> getBloodStock(){
        return bloodStockDB.getStock();
    }

    public Double getAvailableUnits(){
        return bloodStockDB.getAvailableUnits();
    }

    public Double getAvailableUnitsPerType(String type){
        return bloodReqDB.getUnitsTillNowAsPerType(type);
    }
    public List<BloodReportDTO> getBloodReport() {
        List<Object[]> rawResults = bloodStockDB.getBloodReportRaw();
        return rawResults.stream()
                .map(row -> new BloodReportDTO(
                        (String) row[0],
                        ((Number) row[1]).longValue(),
                        ((Number) row[2]).longValue(),
                        ((Number) row[3]).longValue(),
                        ((Number) row[4]).longValue()
                ))
                .collect(Collectors.toList());
    }

    public Double getTotalDonatedUnits(){
        return bloodReqDB.getUnitsTillNowAsPerType("donate");
    }

    public Double getTotalReceivedUnits(){
        return bloodReqDB.getUnitsTillNowAsPerType("request");
    }

    public List<BloodRequestModel> getAllRequests(){
        return bloodReqDB.getAllRequests();
    }

    public Double getAvailableUnitByBloodGroup(String bloodGroup){
        return bloodStockDB.getAvlQuantityByBloodGroup(bloodGroup);
    }

    public LocalDateTime getLastloginDate(){
        return userDatabase.getLastLoginDate("admin123");
    }

    public void insertDefaultBloodGroups() {
        List<String> bloodGroups = List.of("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");

        for (String group : bloodGroups) {
            if (!bloodStockDB.existsByBloodGroup(group)) {
                bloodStockDB.insertBloodStock(
                        group,
                        0.0,
                        LocalDateTime.now()
                );
            }
        }
    }

    public String getUserNameFromId(Long id){
        return bloodReqDB.getUserNameFromId(id);
    }
}
