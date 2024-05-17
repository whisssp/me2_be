package com.me2.rest.admin.mapper;

import com.me2.entity.ProductVariantEntity;
import com.me2.rest.admin.vm.ProductVariantAdminVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductGalleryAdminVMMapper.class})
public interface ProductVariantAdminVMMapper extends EntityMapper<ProductVariantAdminVM, ProductVariantEntity> {
}