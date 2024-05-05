package com.me2.service.impl;

import com.me2.entity.CategorieEntity;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.EnumError;
import com.me2.repository.CategoryRepository;
import com.me2.rest.mapper.CategoryVMMapper;
import com.me2.rest.vm.CategoryVM;
import com.me2.service.CategoryService;
import com.me2.service.dto.CategoryDTO;
import com.me2.service.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final CategoryVMMapper categoryVMMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, CategoryVMMapper categoryVMMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.categoryVMMapper = categoryVMMapper;
    }

    @Override
    public void create(List<CategoryDTO> dtoList) {
        log.debug("Request to create categories");
        saveCategories(dtoList);
    }

    @Override
    public CategoryVM update(CategoryDTO dto) {
        log.debug("Request to update categories");
        CategorieEntity entity = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new ErrorHandler(EnumError.CATEGORY_NOT_FOUND));
        List<CategoryVM> children = null;
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setSlug(dto.getSlug());
        if (dto.getChildren() != null && !dto.getChildren().isEmpty()) {
            children = categoryVMMapper.toDto(saveChildren(dto, entity.getId()));
        }
        CategoryVM vm = categoryVMMapper.toDto(categoryRepository.save(entity));
        vm.setChildren(children);
        entity.setParentCategoryId(dto.getParentCategoryId());
        if (entity.getParentCategoryId() == null) {
            vm.setChildren(categoryVMMapper.toDto(categoryRepository.findAllByParentCategoryId(entity.getId())));
        }
        return vm;
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
  
    private void saveCategories(List<CategoryDTO> categoryDTOList) {
        categoryDTOList.parallelStream().forEach(c -> {
            CategorieEntity entity = categoryMapper.toEntity(c);
            entity.setParentCategoryId(null);
            entity = categoryRepository.save(entity);
            saveChildren(c, entity.getId());
        });
    }

    private List<CategorieEntity> saveChildren(CategoryDTO c, Long parentId) {
        List<CategorieEntity> children = categoryMapper.toEntity(c.getChildren());
        children.forEach(e -> e.setParentCategoryId(parentId));
        return categoryRepository.saveAll(children);
    }
}