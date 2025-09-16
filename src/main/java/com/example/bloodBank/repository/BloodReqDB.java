package com.example.bloodBank.repository;

import com.example.bloodBank.models.BloodRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BloodReqDB extends JpaRepository<BloodRequestModel, String> {

    @Query(value = "SELECT b FROM blood_request_model  b WHERE b.created_by = :createdBy",nativeQuery = true)
    BloodRequestModel getBloodRequestModelByUserName(@Param("createdBy") String createdBy);

    @Query(value = "SELECT b.* FROM blood_request_model b " +
            "JOIN user_model u ON b.created_by = u.user_name " +
            "WHERE u.is_locked = :isLocked", nativeQuery = true)
    List<BloodRequestModel> getBloodRequestModelListByStatus(@Param("isLocked") boolean status);

    @Query(value="SELECT * FROM blood_request_model",nativeQuery = true)
    List<BloodRequestModel> getAllRequests();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO blood_request_model (blood_group, quantity, created_on, created_by, updated_by, updated_time, status, type,name,dob) " +
            "VALUES (:bloodGroup, :quantity, :createdOn, :createdBy, :updatedBy, :updatedTime, :status, :type,:name,:dob)", nativeQuery = true)
    void saveBloodRequest(@Param("bloodGroup") String bloodGroup,
                          @Param("quantity") Double quantity,
                          @Param("createdOn") LocalDateTime createdOn,
                          @Param("createdBy") String createdBy,
                          @Param("updatedBy") String updatedBy,
                          @Param("updatedTime") LocalDateTime updatedTime,
                          @Param("status") String status,
                          @Param("type") String type,
                          @Param("name") String name,
                          @Param("dob") LocalDate dob
    );

    @Query(value = "SELECT * FROM blood_request_model brm WHERE brm.created_by = :userName", nativeQuery = true)
    List<BloodRequestModel> getListByCreatedBy(@Param("userName") String userName);

    @Query(value = "SELECT * FROM blood_request_model brm WHERE brm.status = :status", nativeQuery = true)
    List<BloodRequestModel> getListByRequestStatus(@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE blood_request_model SET status = :status WHERE id = :requestId", nativeQuery = true)
    void updateReqStatus(@Param("requestId") Long requestId, @Param("status") String status);


    @Query(value = "SELECT SUM(b.quantity) FROM blood_request_model b WHERE b.type=:type", nativeQuery = true)
    Double getUnitsTillNowAsPerType(@Param("type") String type);

    @Query(value = "SELECT COUNT(*) > 0 FROM blood_request_model br WHERE br.created_by = :userName AND br.status = 'pending' AND " +
            "br.type = 'donate'", nativeQuery = true)
    boolean checkIfDonateStatusIsPending(@Param("userName") String userName);

    @Query(value = "SELECT COUNT(*) > 0 FROM blood_request_model br WHERE br.created_by = :userName AND br.status = 'pending' AND " +
            "br.type = 'request'", nativeQuery = true)
    boolean checkIfRequestStatusIsPending(@Param("userName")String userName);

    @Query(value = "SELECT br.updated_time FROM blood_request_model br " +
            "WHERE br.type = :type " +
            "AND br.status = :status " +
            "AND br.created_by = :username " +
            "ORDER BY br.updated_time DESC " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<LocalDateTime> getLastUpdatedTimeByTypeStatusAndUsername(@Param("type") String type,
                                                                      @Param("status") String status,
                                                                      @Param("username") String username);

    @Query(value = "SELECT bs.created_by FROM blood_request_model bs WHERE bs.id=:id",nativeQuery = true)
    String getUserNameFromId(@Param("id") Long id);
}
