package com.me2.service.impl;

import com.me2.entity.CategorieEntity;
import com.me2.repository.CategoryRepository;
import com.me2.rest.vm.CategoryVM;
import com.me2.service.CategoryService;
import com.me2.service.dto.CategoryDTO;
import com.me2.service.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void create(List<CategoryDTO> dtoList) {
        log.debug("Request to create categories");
        dtoList.parallelStream().forEach(c -> {
            CategorieEntity entity = categoryMapper.toEntity(c);
            entity.setParentCategoryId(null);
            entity = categoryRepository.save(entity);
            List<CategorieEntity> children = categoryMapper.toEntity(c.getChildren());
            Long pId = entity.getId();
            children.forEach(e -> e.setParentCategoryId(pId));
            categoryRepository.saveAll(children);
        });
    }

    @Override
    public CategoryVM update(List<CategoryDTO> dtoList) {
        return null;
    }

    @Override
    public void delete(List<Long> ids) {

    }

    @Override
    public CategoryVM getOneById(Long id) {
        return null;
    }

    @Override
    public List<CategoryVM> getAll(Pageable pageable) {
        return null;
    }
}