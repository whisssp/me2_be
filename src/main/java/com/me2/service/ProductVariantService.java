package com.me2.service;

import com.me2.rest.admin.vm.ProductVariantAdminVM;
import com.me2.service.dto.admin.ProductVariantAdminDTO;

import java.util.List;
import java.util.Set;

public interface ProductVariantService {

    void save(Set<ProductVariantAdminDTO> variantAdminDTO);

    List<ProductVariantAdminVM> getVariantsByProdId(Long id);
}