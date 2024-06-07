package com.me2.rest.user.mapper;

import com.me2.entity.ProductVariant;
import com.me2.rest.user.vm.ProductVariantUserVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductVariantUserVMMapper extends EntityMapper<ProductVariantUserVM, ProductVariant> {
}
