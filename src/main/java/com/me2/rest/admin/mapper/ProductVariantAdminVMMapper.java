package com.me2.rest.admin.mapper;

import com.me2.entity.ProductVariant;
import com.me2.rest.admin.vm.ProductVariantAdminVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductVariantAdminVMMapper extends EntityMapper<ProductVariantAdminVM, ProductVariant> {
}