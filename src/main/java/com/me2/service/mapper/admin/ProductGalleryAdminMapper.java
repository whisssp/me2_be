package com.me2.service.mapper.admin;

import com.me2.entity.ProductGalleryEntity;
import com.me2.service.dto.admin.ProductGalleryAdminDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductGalleryAdminMapper extends EntityMapper<ProductGalleryAdminDTO, ProductGalleryEntity> {
}