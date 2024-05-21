package com.me2.service.mapper.admin;

import com.me2.entity.Category;
import com.me2.service.dto.admin.CategoryAdminDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryAdminMapper extends EntityMapper<CategoryAdminDTO, Category> {
}