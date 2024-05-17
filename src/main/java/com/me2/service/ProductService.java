package com.me2.service;


import com.me2.entity.ProductEntity;
import com.me2.global.response.Paginate;
import com.me2.rest.vm.ProductVM;
import com.me2.service.dto.admin.ProductAdminDTO;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductEntity save(ProductEntity entity);

    ProductVM save(ProductAdminDTO productAdminDTO);

    ProductVM getProductByIdForAdmin(Long id);

    Paginate<ProductVM> getAllProduct(Pageable pageable);
}