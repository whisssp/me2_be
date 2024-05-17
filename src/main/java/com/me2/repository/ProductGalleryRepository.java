package com.me2.repository;

import com.me2.entity.ProductGalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGalleryRepository extends JpaRepository<ProductGalleryEntity, Long> {
}