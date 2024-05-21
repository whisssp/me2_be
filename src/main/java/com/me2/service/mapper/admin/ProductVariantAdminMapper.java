package com.me2.service.mapper.admin;

import com.me2.entity.ProductVariant;
import com.me2.service.dto.admin.ProductVariantAdminDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductVariantAdminMapper extends EntityMapper<ProductVariantAdminDTO, ProductVariant> {
}