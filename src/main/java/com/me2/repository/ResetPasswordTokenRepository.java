package com.me2.repository;

import com.me2.entity.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {

    @Query("SELECT r FROM ResetPasswordToken r WHERE r.resetCode = :resetCode")
    ResetPasswordToken findByResetCode(String resetCode);

    @Modifying
    @Query("DELETE FROM ResetPasswordToken r WHERE r.user.id = :userId or r.resetCode = :code")
    void deleteByUserId(Long userId, String code);

    void deleteByResetCodeOrUserId(String resetCode, Long userId);

    @Query("SELECT r FROM ResetPasswordToken r WHERE r.user.id = :userId")
    ResetPasswordToken findByUserId(Long userId);



}
