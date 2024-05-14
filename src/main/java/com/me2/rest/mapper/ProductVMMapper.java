package com.me2.rest.mapper;

import com.me2.entity.ProductEntity;
import com.me2.rest.vm.ProductVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductVMMapper extends EntityMapper<ProductVM, ProductEntity> {
}