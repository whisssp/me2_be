package com.me2.service.impl;

import com.me2.entity.Category;
import com.me2.entity.Product;
import com.me2.entity.Promotion;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.EnumError;
import com.me2.global.response.Paginate;
import com.me2.repository.ProductRepository;
import com.me2.rest.admin.mapper.ProductAdminVMMapper;
import com.me2.rest.admin.vm.ProductAdminVM;
import com.me2.service.CategoryService;
import com.me2.service.ProductService;
import com.me2.service.ProductVariantService;
import com.me2.service.PromotionService;
import com.me2.service.dto.admin.ProductAdminDTO;
import com.me2.service.dto.admin.ProductVariantAdminDTO;
import com.me2.service.mapper.admin.ProductAdminMapper;
import com.me2.service.mapper.admin.ProductVariantAdminMapper;
import com.me2.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductAdminVMMapper productAdminVMMapper;

    private final ProductAdminMapper productAdminMapper;

    private final ProductVariantService variantService;

    private final CategoryService categoryService;

    private final PromotionService promotionService;

    private final ProductVariantAdminMapper variantAdminMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductAdminVMMapper productAdminVMMapper,
                              ProductAdminMapper productAdminMapper,
                              ProductVariantService variantService,
                              CategoryService categoryService,
                              PromotionService promotionService,
                              ProductVariantAdminMapper variantAdminMapper) {
        this.productRepository = productRepository;
        this.productAdminVMMapper = productAdminVMMapper;
        this.productAdminMapper = productAdminMapper;
        this.variantService = variantService;
        this.categoryService = categoryService;
        this.promotionService = promotionService;
        this.variantAdminMapper = variantAdminMapper;
    }

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    @Transactional
    public Product save(ProductAdminDTO productAdminDTO) {
        Product entity = null;
        if (productAdminDTO.getId() == null) {
            log.info(">>>>Product={}", productAdminDTO);
            entity = createProduct(productAdminDTO);
            saveVariants(entity, productAdminDTO);
            log.info(">>>>Product={}", entity);
        } else {
            entity = productRepository.findById(productAdminDTO.getId())
                    .orElseThrow(() -> new ErrorHandler(EnumError.PRODUCT_NOT_FOUND));
            productAdminMapper.partialUpdate(entity, productAdminDTO);
            entity = productRepository.saveAndFlush(entity);
        }
        return entity;
    }

    private Product createProduct(ProductAdminDTO productAdminDTO) {
        Promotion promotion = null;
        Product entity = productAdminMapper.toEntity(productAdminDTO);
        if (productAdminDTO.getCategoryId() == null) {
            throw new ErrorHandler(EnumError.CATEGORY_NOT_FOUND);
        }
        Category category = categoryService.findById(productAdminDTO.getCategoryId());
        if (productAdminDTO.getPromotionId() != null)
            promotion = promotionService.findById(productAdminDTO.getPromotionId());
        entity.setCategory(category);
        entity.setPromotion(promotion);
//        entity.setProductVariants(variantAdminMapper.toEntity(new ArrayList<>(productAdminDTO.getProductVariants())));
        entity = productRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    public ProductAdminVM getProductByIdForAdmin(Long id) {
        return productAdminVMMapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(EnumError.PRODUCT_NOT_FOUND)));
    }

    @Override
    public Paginate<ProductAdminVM> getAllProduct(Pageable pageable) {
        return new PageUtil<ProductAdminVM>()
                .toPaginateResponse(productRepository.findAll(pageable)
                        .map(productAdminVMMapper::toDto));
    }

    private void saveVariants(Product product, ProductAdminDTO dto) {
        if (product == null) return;
        dto.getProductVariants().forEach(v -> v.setProduct(product));
        variantService.save(dto.getProductVariants());
    }
}