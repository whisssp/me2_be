package com.me2.rest.mapper;

import com.me2.entity.CategorieEntity;
import com.me2.rest.vm.CategoryVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryVMMapper extends EntityMapper<CategoryVM, CategorieEntity> {
}