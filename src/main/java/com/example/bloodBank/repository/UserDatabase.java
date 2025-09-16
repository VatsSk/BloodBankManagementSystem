package com.example.bloodBank.repository;

import com.example.bloodBank.models.UserModel;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserDatabase extends JpaRepository<UserModel, Long> {


    @Query(value = "SELECT u FROM User_model u WHERE u.id = :id",nativeQuery = true)
    UserModel getUser(@Param("id") Long id);

    @Query(value = "SELECT u.email FROM User_model u WHERE u.user_name = :userName",nativeQuery = true)
    String getEmailFromUserName(@Param("userName") String userName);

    @Query(value = "SELECT * FROM User_model u WHERE u.is_locked = :status AND role ='ENDUSER' ",nativeQuery = true)
    List<UserModel> getAllUser(@Param("status")boolean status);

    @Query(value = "SELECT * FROM user_model WHERE role ='ENDUSER'",nativeQuery = true)
    List<UserModel> getAllUser();

    @Query(value = "SELECT COUNT(*) > 0 FROM user_model WHERE user_name = :userName", nativeQuery = true)
    boolean existsByUserName(@Param("userName") String userName);

    @Query(value = "SELECT COUNT(*) > 0 FROM user_model WHERE email = :email", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user_model u WHERE u.user_name = :userName",nativeQuery = true)
    UserModel getByUserName(@Param("userName") String userName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_model (user_name, created_by, first_name, last_name, dob, password, created_on, role, is_locked, login_attempts, email, security_question1, security_answer1, security_question2, security_answer2, security_question3, security_answer3, blood_group) " +
            "VALUES (:userName, :createdBy, :firstName, :lastName, :dob, :password, :createdOn, :role, :isLocked, :loginAttempts, :email, :securityQuestion1, :securityAnswer1, :securityQuestion2, :securityAnswer2, :securityQuestion3, :securityAnswer3, :bloodGroup)",
            nativeQuery = true)
    void insertUser(
            @Param("userName") String userName,
            @Param("createdBy") String createdBy,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("dob") LocalDate dob,
            @Param("password") String password,
            @Param("createdOn") LocalDateTime createdOn,
            @Param("role") String role,
            @Param("isLocked") boolean isLocked,
            @Param("loginAttempts") int loginAttempts,
            @Param("email") String email,
            @Param("securityQuestion1") String securityQuestion1,
            @Param("securityAnswer1") String securityAnswer1,
            @Param("securityQuestion2") String securityQuestion2,
            @Param("securityAnswer2") String securityAnswer2,
            @Param("securityQuestion3") String securityQuestion3,
            @Param("securityAnswer3") String securityAnswer3,
            @Param("bloodGroup")String bloodGroup
    );


    @Modifying
    @Transactional
    @Query(value = "UPDATE user_model SET password = :newPassword WHERE id = :userId", nativeQuery = true)
    void updatePasswordById(@Param("userId") Long userId, @Param("newPassword") String newPassword);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_model SET login_attempts = :loginAttempts WHERE id = :id", nativeQuery = true)
    void updateLoginAttemptsById(@Param("id") Long id, @Param("loginAttempts") int loginAttempts);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_model SET is_locked = :isLocked WHERE id = :id", nativeQuery = true)
    void updateIsLockedById(@Param("id") Long id, @Param("isLocked") boolean isLocked);


    @Query(value = "SELECT COUNT(*) FROM user_model WHERE is_locked =:isLocked ", nativeQuery = true)
    int countActiveUsers(@Param("isLocked")boolean isLocked);

    @Query(value = "SELECT * FROM user_model WHERE email = :email", nativeQuery = true)
    UserModel getUserByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_model SET last_login_date =:currentDate WHERE user_name=:username",nativeQuery = true)
    void updateLastLoginDetail(@Param("username") String username,
            @Param("currentDate") LocalDateTime currentDate );

    @Query(value = "SELECT last_login_date FROM user_model WHERE user_name = :username", nativeQuery = true)
    LocalDateTime getLastLoginDate(@Param("username") String username);

}
