package com.me2.service.impl;

import com.me2.entity.ProductGallery;
import com.me2.entity.ProductVariant;
import com.me2.repository.ProductVariantRepository;
import com.me2.rest.admin.vm.ProductVariantAdminVM;
import com.me2.service.ProductGalleryService;
import com.me2.service.ProductVariantService;
import com.me2.service.dto.admin.ProductGalleryAdminDTO;
import com.me2.service.dto.admin.ProductVariantAdminDTO;
import com.me2.service.mapper.admin.ProductVariantAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

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
            ProductVariant entity = variantAdminMapper.toEntity(e);
            log.info(">>>VARIANT={}", entity);
            entity = variantRepository.saveAndFlush(entity);
            log.info(">>>VARIANT-saved={}", entity);
            this.saveGallery(entity.getId(), e.getProductGalleries());
        });
    }

    @Override
    public List<ProductVariantAdminVM> getVariantsByProdId(Long id) {
        return null;
    }

    private void saveGallery(Long variantId, List<ProductGalleryAdminDTO> galleryAdminDTOS) {
        if (variantId == null) return;
        galleryAdminDTOS.forEach(g -> g.setProductVariantId(variantId));
        galleryService.save(galleryAdminDTOS);
    }
}