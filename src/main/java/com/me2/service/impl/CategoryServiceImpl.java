package com.me2.service.impl;

import com.me2.entity.CategorieEntity;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.EnumError;
import com.me2.repository.CategoryRepository;
import com.me2.rest.mapper.CategoryVMMapper;
import com.me2.rest.response.Paginate;
import com.me2.rest.vm.CategoryVM;
import com.me2.service.CategoryService;
import com.me2.service.dto.CategoryDTO;
import com.me2.service.mapper.CategoryMapper;
import com.me2.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        categoryMapper.partialUpdate(entity, dto);
        if (dto.getChildren() != null && !dto.getChildren().isEmpty()) {
            children = categoryVMMapper.toDto(saveChildren(dto, entity.getId()));
        }
        CategoryVM vm = categoryVMMapper.toDto(categoryRepository.save(entity));
        vm.setChildren(children);

        return vm;
    }

    @Override
    public void delete(List<Long> ids) {
        log.debug("Request to delete categories in list id");
        Map<Long, List<Long>> mapId = new HashMap<>();
        ids.parallelStream().forEach(i -> mapId.put(i, toIdList(categoryRepository.findAllByParentCategoryId(i))));
        mapId.forEach((k, v) -> {
            categoryRepository.deleteAllById(v);
            categoryRepository.deleteById(k);
        });

    }

    @Override
    public CategoryVM getOneById(Long id) {
        log.debug("Request to get category by id: {}", id);
        return categoryVMMapper.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(EnumError.CATEGORY_NOT_FOUND))
        );
    }

    @Override
    public Paginate<CategoryVM> getAll(Pageable pageable) {
        log.debug("Request to find all categories");
        return new PageUtil<CategoryVM>()
                .toPaginateResponse(categoryRepository.findAll(pageable)
                        .map(categoryVMMapper::toDto));
    }
  
    private void saveCategories(List<CategoryDTO> categoryDTOList) {
        categoryDTOList.parallelStream().forEach(c -> {
            CategorieEntity entity = categoryMapper.toEntity(c);
            entity.setParentCategoryId(null);
            entity = categoryRepository.save(entity);
            log.info("Parent Id: {}", entity.getId());
            saveChildren(c, entity.getId());
        });
    }

    private List<CategorieEntity> saveChildren(CategoryDTO c, Long parentId) {
        if (c.getChildren() == null || c.getChildren().isEmpty() || parentId == null) return null;
        List<CategorieEntity> children = categoryMapper.toEntity(c.getChildren());
        children.forEach(e -> e.setParentCategoryId(parentId));
        return categoryRepository.saveAllAndFlush(children);
    }

    private Paginate<CategoryVM> toPaginateResponse(Page<CategorieEntity> page) {
        Paginate<CategoryVM> data = new Paginate<>();
        data.setPageNumber(page.getNumber());
        data.setData(categoryVMMapper.toDto(page.getContent()));
        data.setPageSize(page.getSize());
        data.setTotalPages(page.getTotalPages());
        data.setTotalElements(page.getTotalElements());
        return data;
    }

    private List<Long> toIdList(List<CategorieEntity> categories) {
        return categories.stream().map(CategorieEntity::getId).toList();
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