package com.me2.rest.admin.mapper;

import com.me2.entity.Category;
import com.me2.rest.admin.vm.CategoryAdminVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryAdminVMMapper extends EntityMapper<CategoryAdminVM, Category> {
}