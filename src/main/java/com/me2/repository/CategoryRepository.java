package com.me2.repository;

import com.me2.entity.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategorieEntity, Long> {

    List<CategorieEntity> findAllByParentCategoryId(Long id);
}