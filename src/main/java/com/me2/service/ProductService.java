package com.me2.service;


import com.me2.entity.Product;
import com.me2.global.response.Paginate;
import com.me2.rest.admin.vm.ProductAdminVM;
import com.me2.service.dto.admin.ProductAdminDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product save(Product entity);

    Product save(ProductAdminDTO productAdminDTO);

    ProductAdminVM getProductByIdForAdmin(Long id);

    Paginate<ProductAdminVM> getAllProduct(Pageable pageable);

    void removeByIdsForAdmin(List<Long> ids);

    void activeByIdsForAdmin(List<Long> ids);

    void approveByIdsForAdmin(List<Long> ids);
}