package com.me2.service.impl;

import com.me2.entity.Category;
import com.me2.entity.Product;
import com.me2.entity.ProductVariant;
import com.me2.entity.Promotion;
import com.me2.exception.CustomException;
import com.me2.global.enums.ActionStatus;
import com.me2.global.enums.EnumError;
import com.me2.global.response.Paginate;
import com.me2.repository.ProductRepository;
import com.me2.rest.admin.mapper.ProductAdminVMMapper;
import com.me2.rest.admin.vm.ProductAdminVM;
import com.me2.rest.admin.vm.ProductVariantAdminVM;
import com.me2.service.CategoryService;
import com.me2.service.ProductService;
import com.me2.service.ProductVariantService;
import com.me2.service.PromotionService;
import com.me2.service.dto.admin.ProductAdminDTO;
import com.me2.service.mapper.admin.ProductAdminMapper;
import com.me2.service.mapper.admin.ProductVariantAdminMapper;
import com.me2.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductAdminVMMapper productAdminVMMapper,
                              ProductAdminMapper productAdminMapper,
                              ProductVariantService variantService,
                              CategoryService categoryService,
                              PromotionService promotionService) {
        this.productRepository = productRepository;
        this.productAdminVMMapper = productAdminVMMapper;
        this.productAdminMapper = productAdminMapper;
        this.variantService = variantService;
        this.categoryService = categoryService;
        this.promotionService = promotionService;
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
            log.info(">>>>Product-saved={}", entity);
        } else {
            entity = productRepository.findById(productAdminDTO.getId())
                    .orElseThrow(() -> new CustomException(EnumError.PRODUCT_NOT_FOUND));
            productAdminMapper.partialUpdate(entity, productAdminDTO);
            entity = productRepository.saveAndFlush(entity);
        }
        return entity;
    }

    private Product createProduct(ProductAdminDTO productAdminDTO) {
        Promotion promotion = null;
        Product entity = productAdminMapper.toEntity(productAdminDTO);
        if (productAdminDTO.getCategoryId() == null) {
            throw new CustomException(EnumError.CATEGORY_NOT_FOUND);
        }
        Category category = categoryService.findById(productAdminDTO.getCategoryId());
        if (productAdminDTO.getPromotionId() != null)
            promotion = promotionService.findById(productAdminDTO.getPromotionId());
        entity.setCategory(category);
        entity.setPromotion(promotion);
//        entity.setStatus(ActionStatus.WAITING_FOR_APPROVAL);
//        entity.setIsActivated(false);
//        entity.setProductVariants(variantAdminMapper.toEntity(new ArrayList<>(productAdminDTO.getProductVariants())));
        entity = productRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    public ProductAdminVM getProductByIdForAdmin(Long id) {
        return productAdminVMMapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new CustomException(EnumError.PRODUCT_NOT_FOUND)));
    }


    // Need filter to find all
    @Override
    public Paginate<ProductAdminVM> getAllProduct(Pageable pageable) {
        return PageUtil
                .toPaginateResponse(productRepository.findAll(pageable)
                        .map(this::toProductAdminVM));
    }

    @Override
    public void removeByIdsForAdmin(List<Long> ids) {
        log.debug("Request to remove products: {}", ids);
        List<Product> products = productRepository.findAllById(ids);
        products.parallelStream().forEach(p -> {
            p.setStatus(ActionStatus.DELETED);
            p.setIsActivated(false);
        });
        productRepository.saveAll(products);
    }

    @Override
    public void activeByIdsForAdmin(List<Long> ids) {
        log.debug("Request to set active products: {}", ids);
        List<Product> products = productRepository.findAllById(ids)
                .parallelStream()
                .peek(p -> {
                    if (p.getStatus().equals(ActionStatus.WAITING_FOR_APPROVAL) || p.getStatus().equals(ActionStatus.DELETED))
                        throw new CustomException(EnumError.PRODUCT_ACTIVATED_FAILED);
                    p.setIsActivated(true);
                }).toList();
        productRepository.saveAll(products);
    }

    @Override
    public void approveByIdsForAdmin(List<Long> ids) {
        log.debug("Request to approve products: {}", ids);
        List<Product> products = productRepository.findAllById(ids)
                .parallelStream()
                .peek(p -> {
                    if (!p.getStatus().equals(ActionStatus.WAITING_FOR_APPROVAL))
                        throw new CustomException(EnumError.PRODUCT_APPROVAL_FAILED);
                    p.setStatus(ActionStatus.APPROVAL);
                }).toList();
        productRepository.saveAll(products);
    }

    private void saveVariants(Product product, ProductAdminDTO dto) {
        if (product == null) return;
        dto.getProductVariants().forEach(v -> v.setProduct(product));
        variantService.save(dto.getProductVariants());
    }

    private ProductAdminVM toProductAdminVM(Product product) {
        if (product == null) return null;
        ProductAdminVM vm = productAdminVMMapper.toDto(product);
        this.setProductIdForVariants(vm.getProductVariants(), product.getId());
        return vm;
    }

    private void setProductIdForVariants(List<ProductVariantAdminVM> variantAdminVMs, Long productId) {
        if (variantAdminVMs == null || variantAdminVMs.isEmpty() || productId == null) return;
        variantAdminVMs.parallelStream().forEach(v -> v.setProductId(productId));
    }
}