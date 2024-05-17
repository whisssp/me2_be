package com.me2.service.impl;

import com.me2.entity.ProductVariantEntity;
import com.me2.repository.ProductVariantRepository;
import com.me2.rest.admin.vm.ProductVariantAdminVM;
import com.me2.service.ProductGalleryService;
import com.me2.service.ProductVariantService;
import com.me2.service.dto.admin.ProductGalleryAdminDTO;
import com.me2.service.dto.admin.ProductVariantAdminDTO;
import com.me2.service.mapper.admin.ProductVariantAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository variantRepository;

    private final ProductGalleryService galleryService;

    private final ProductVariantAdminMapper variantAdminMapper;

    public ProductVariantServiceImpl(ProductVariantRepository variantRepository, ProductGalleryService galleryService, ProductVariantAdminMapper variantAdminMapper) {
        this.variantRepository = variantRepository;
        this.galleryService = galleryService;
        this.variantAdminMapper = variantAdminMapper;
    }

    @Override
    public void save(Set<ProductVariantAdminDTO> variantAdminDTOs) {
        variantAdminDTOs.forEach(e -> {
            ProductVariantEntity entity = variantAdminMapper.toEntity(e);
            log.info(">>>VARIANT={}", entity);
            entity = variantRepository.saveAndFlush(entity);
            this.saveGallery(entity.getId(), e.getGallery());
        });
    }

    @Override
    public List<ProductVariantAdminVM> getVariantsByProdId(Long id) {
        return null;
    }

    private void saveGallery(Long variantId, ProductGalleryAdminDTO galleryAdminDTOS) {
        if (variantId == null) return;
        galleryAdminDTOS.setProductVariantId(variantId);
        galleryService.save(List.of(galleryAdminDTOS));
    }
}