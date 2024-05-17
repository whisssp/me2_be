package com.me2.service.impl;

import com.me2.entity.ProductEntity;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.EnumError;
import com.me2.global.response.Paginate;
import com.me2.repository.ProductRepository;
import com.me2.rest.admin.mapper.ProductAdminVMMapper;
import com.me2.rest.vm.ProductVM;
import com.me2.service.ProductService;
import com.me2.service.ProductVariantService;
import com.me2.service.dto.admin.ProductAdminDTO;
import com.me2.service.dto.admin.ProductVariantAdminDTO;
import com.me2.service.mapper.admin.ProductAdminMapper;
import com.me2.util.PageUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductAdminVMMapper productAdminVMMapper;

    private final ProductAdminMapper productAdminMapper;

    private final ProductVariantService variantService;

    public ProductServiceImpl(ProductRepository productRepository, ProductAdminVMMapper productAdminVMMapper, ProductAdminMapper productAdminMapper, ProductVariantService variantService) {
        this.productRepository = productRepository;
        this.productAdminVMMapper = productAdminVMMapper;
        this.productAdminMapper = productAdminMapper;
        this.variantService = variantService;
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        return productRepository.save(entity);
    }

    @Override
    @Transactional
    public ProductVM save(ProductAdminDTO productAdminDTO) {
        ProductEntity entity = null;
        if (productAdminDTO.getId() == null) {
            entity = productRepository.saveAndFlush(productAdminMapper.toEntity(productAdminDTO));
        } else {
            entity = productRepository.findById(productAdminDTO.getId())
                    .orElseThrow(() -> new ErrorHandler(EnumError.PRODUCT_NOT_FOUND));
            productAdminMapper.partialUpdate(entity, productAdminDTO);
            entity = productRepository.saveAndFlush(entity);
        }
        saveVariants(entity.getId(), productAdminDTO);
        return productAdminVMMapper.toDto(entity);
    }

    @Override
    public ProductVM getProductByIdForAdmin(Long id) {
        return productAdminVMMapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(EnumError.PRODUCT_NOT_FOUND)));
    }

    @Override
    public Paginate<ProductVM> getAllProduct(Pageable pageable) {
        return new PageUtil<ProductVM>()
                .toPaginateResponse(productRepository.findAll(pageable)
                        .map(productAdminVMMapper::toDto));
    }

    private void saveVariants(Long productId, ProductAdminDTO dto) {
        if (productId == null) return;
        dto.getVariants().parallelStream().forEach(v -> v.setProductId(productId));
        variantService.save(dto.getVariants());
    }
}