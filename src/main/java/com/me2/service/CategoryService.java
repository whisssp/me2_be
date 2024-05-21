package com.me2.service;

import com.me2.entity.Category;
import com.me2.global.response.Paginate;
import com.me2.rest.admin.vm.CategoryAdminVM;
import com.me2.service.dto.admin.CategoryAdminDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    void create(List<CategoryAdminDTO> dtoList);

    CategoryAdminVM update(CategoryAdminDTO dto);

    void delete(List<Long> ids);

    CategoryAdminVM getOneById(Long id);

    Paginate<CategoryAdminVM> getAll(Pageable pageable);

    Category findById(Long id);
}