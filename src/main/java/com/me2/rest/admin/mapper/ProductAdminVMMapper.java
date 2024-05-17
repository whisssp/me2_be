package com.me2.rest.admin.mapper;

import com.me2.entity.ProductEntity;
import com.me2.rest.vm.ProductVM;
import com.me2.rest.admin.vm.ProductVariantAdminVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductAdminVMMapper extends EntityMapper<ProductVM, ProductEntity> {
}