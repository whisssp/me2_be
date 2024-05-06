package com.me2.service;

import com.me2.entity.CategorieEntity;
import com.me2.rest.response.Paginate;
import com.me2.rest.vm.CategoryVM;
import com.me2.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    void create(List<CategoryDTO> dtoList);

    CategoryVM update(CategoryDTO dto);

    void delete(List<Long> ids);

    CategoryVM getOneById(Long id);

    Paginate<CategoryVM> getAll(Pageable pageable);
}