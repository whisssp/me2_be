package com.me2.service.impl;

import com.me2.global.response.Paginate;
import com.me2.repository.ProductRepository;
import com.me2.rest.mapper.ProductVMMapper;
import com.me2.rest.vm.ProductVM;
import com.me2.service.ProductService;
import com.me2.service.dto.admin.ProductAdminDTO;
import com.me2.service.mapper.admin.ProductAdminMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductVMMapper productVMMapper;

    private final ProductAdminMapper productAdminMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductVMMapper productVMMapper, ProductAdminMapper productAdminMapper) {
        this.productRepository = productRepository;
        this.productVMMapper = productVMMapper;
        this.productAdminMapper = productAdminMapper;
    }

    @Override
    public void create(ProductAdminDTO productAdminDTO) {

    }

    @Override
    public ProductVM update(ProductAdminDTO productAdminDTO) {
        return null;
    }

    @Override
    public ProductVM getProductByIdForAdmin(Long id) {
        return null;
    }

    @Override
    public Paginate<ProductVM> getAllProduct(Pageable pageable) {
        return null;
    }
}