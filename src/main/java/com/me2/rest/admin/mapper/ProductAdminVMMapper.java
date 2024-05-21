package com.me2.rest.admin.mapper;

import com.me2.entity.Product;
import com.me2.rest.admin.vm.ProductAdminVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductAdminVMMapper extends EntityMapper<ProductAdminVM, Product> {
}