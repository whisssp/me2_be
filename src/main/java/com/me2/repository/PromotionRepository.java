package com.me2.repository;

import com.me2.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {

    PromotionEntity findPromotionEntityByCode(String code);
}