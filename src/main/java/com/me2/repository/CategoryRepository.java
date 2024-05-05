package com.me2.repository;

import com.me2.entity.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategorieEntity, Long> {
}