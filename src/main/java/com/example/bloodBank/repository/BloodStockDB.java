package com.example.bloodBank.repository;

import com.example.bloodBank.models.BloodStockModel;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BloodStockDB extends JpaRepository<BloodStockModel,Long> {

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM blood_stock_model WHERE blood_group = :bloodGroup", nativeQuery = true)
    boolean existsByBloodGroup(@Param("bloodGroup") String bloodGroup);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO blood_stock_model (blood_group, available_units, last_updated_info) " +
            "VALUES (:bloodGroup, :availableUnits, :lastUpdatedInfo)", nativeQuery = true)
    void insertBloodStock(@Param("bloodGroup") String bloodGroup,
                          @Param("availableUnits") Double availableUnits,
                          @Param("lastUpdatedInfo") LocalDateTime lastUpdatedInfo);


    @Query(value = "SELECT br.blood_group, " +
            "COUNT(CASE WHEN br.status = 'Approved' THEN 1 END) AS approvedRequests, " +
            "COUNT(CASE WHEN br.status = 'Rejected' THEN 1 END) AS rejectedRequests, " +
            "COUNT(CASE WHEN br.status = 'pending' THEN 1 END) AS pendingRequests, " +
            "COUNT(br.id) AS totalRequests " +
            "FROM blood_request_model br " +
            "GROUP BY br.blood_group " +
            "ORDER BY br.blood_group", nativeQuery = true)
    List<Object[]> getBloodReportRaw();

    @Modifying
     @Transactional
    @Query(value = "UPDATE blood_stock_model bsm " +
            "SET bsm.available_units = bsm.available_units + :quantity, " +
            "bsm.last_updated_info = :lastUpdatedInfo " +
            "WHERE bsm.blood_group = :bloodGroup",
            nativeQuery = true)
    void updateDonateStock(@Param("bloodGroup") String bloodGroup,
                          @Param("quantity") Double quantity,
                          @Param("lastUpdatedInfo") LocalDateTime lastUpdatedInfo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE blood_stock_model bsm " +
            "SET bsm.available_units = bsm.available_units - :quantity, " +
            "bsm.last_updated_info = :lastUpdatedInfo " +
            "WHERE bsm.blood_group = :bloodGroup",
            nativeQuery = true)
    void updateRequestStock(@Param("bloodGroup") String bloodGroup,
                           @Param("quantity") Double quantity,
                           @Param("lastUpdatedInfo") LocalDateTime lastUpdatedInfo);

     @Query(value = "SELECT * FROM blood_stock_model",nativeQuery = true)
     List<BloodStockModel> getStock();

     @Query(value = "SELECT SUM(available_units) FROM blood_stock_model",nativeQuery = true)
     Double getAvailableUnits();

     @Query(value = "SELECT available_units FROM blood_stock_model WHERE blood_group=:bloodGroup",nativeQuery = true)
     Double getAvlQuantityByBloodGroup(@Param("bloodGroup")String bloodGroup);

}
