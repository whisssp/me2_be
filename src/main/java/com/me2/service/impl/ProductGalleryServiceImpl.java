package com.me2.service.impl;


import com.me2.entity.ProductGalleryEntity;
import com.me2.repository.ProductGalleryRepository;
import com.me2.service.ProductGalleryService;
import com.me2.service.dto.admin.ProductGalleryAdminDTO;
import com.me2.service.mapper.admin.ProductGalleryAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProductGalleryServiceImpl implements ProductGalleryService {

    private final ProductGalleryAdminMapper galleryMapper;

    private final ProductGalleryRepository galleryRepository;

    public ProductGalleryServiceImpl(ProductGalleryAdminMapper galleryMapper, ProductGalleryRepository galleryRepository) {
        this.galleryMapper = galleryMapper;
        this.galleryRepository = galleryRepository;
    }

    @Override
    public void save(List<ProductGalleryAdminDTO> galleryAdminDTOs) {
        log.info("galleries dto: {}", galleryAdminDTOs.toString());
        List<ProductGalleryEntity> entities = galleryAdminDTOs.stream()
                .map(galleryMapper::toEntity)
                .toList();
        log.info("galleries: {}", galleryAdminDTOs.toString());
        galleryRepository.saveAll(entities);
    }
}