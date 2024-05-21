package com.me2.rest.admin.mapper;

import com.me2.entity.ProductGallery;
import com.me2.rest.admin.vm.ProductGalleryAdminVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductGalleryAdminVMMapper extends EntityMapper<ProductGalleryAdminVM, ProductGallery> {
}