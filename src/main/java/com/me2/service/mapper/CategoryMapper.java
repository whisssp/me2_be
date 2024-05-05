package com.me2.service.mapper;

import com.me2.entity.CategorieEntity;
import com.me2.service.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, CategorieEntity> {
}