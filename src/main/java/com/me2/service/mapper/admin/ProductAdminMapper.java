package com.me2.service.mapper.admin;

import com.me2.entity.Product;
import com.me2.service.dto.admin.ProductAdminDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {})
public interface ProductAdminMapper extends EntityMapper<ProductAdminDTO, Product> {
}