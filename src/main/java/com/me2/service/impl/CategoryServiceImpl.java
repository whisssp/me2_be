package com.me2.service.impl;

import com.me2.entity.Category;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.EnumError;
import com.me2.repository.CategoryRepository;
import com.me2.rest.admin.mapper.CategoryAdminVMMapper;
import com.me2.global.response.Paginate;
import com.me2.rest.admin.vm.CategoryAdminVM;
import com.me2.service.CategoryService;
import com.me2.service.dto.admin.CategoryAdminDTO;
import com.me2.service.mapper.admin.CategoryAdminMapper;
import com.me2.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryAdminMapper categoryAdminMapper;

    private final CategoryAdminVMMapper categoryAdminVMMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryAdminMapper categoryAdminMapper, CategoryAdminVMMapper categoryAdminVMMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryAdminMapper = categoryAdminMapper;
        this.categoryAdminVMMapper = categoryAdminVMMapper;
    }

    @Override
    public void create(List<CategoryAdminDTO> dtoList) {
        log.debug("Request to create categories");
        saveCategories(dtoList);
    }

    @Override
    public CategoryAdminVM update(CategoryAdminDTO dto) {
        log.debug("Request to update categories");
        Category entity = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new ErrorHandler(EnumError.CATEGORY_NOT_FOUND));
        List<CategoryAdminVM> children = null;
        categoryAdminMapper.partialUpdate(entity, dto);
        if (dto.getChildren() != null && !dto.getChildren().isEmpty()) {
            children = categoryAdminVMMapper.toDto(saveChildren(dto, entity));
        }
        CategoryAdminVM vm = categoryAdminVMMapper.toDto(categoryRepository.save(entity));
        vm.setChildren(children);
        return vm;
    }

    @Override
    public void delete(List<Long> ids) {
        log.debug("Request to delete categories in list id");
        Map<Long, List<Long>> mapId = new HashMap<>();
        ids.parallelStream().forEach(i -> mapId.put(i, toIdList(categoryRepository.findAllByParent_Id(i))));
        mapId.forEach((k, v) -> {
            categoryRepository.deleteAllById(v);
            categoryRepository.deleteById(k);
        });

    }

    @Override
    public CategoryAdminVM getOneById(Long id) {
        log.debug("Request to get category by id: {}", id);
        return categoryAdminVMMapper.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(EnumError.CATEGORY_NOT_FOUND))
        );
    }

    @Override
    public Paginate<CategoryAdminVM> getAll(Pageable pageable) {
        log.debug("Request to find all categories");
        return new PageUtil<CategoryAdminVM>()
                .toPaginateResponse(categoryRepository.findAll(pageable)
                        .map(categoryAdminVMMapper::toDto));
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ErrorHandler(EnumError.CATEGORY_NOT_FOUND));
    }

    private void saveCategories(List<CategoryAdminDTO> categoryAdminDTOList) {
        categoryAdminDTOList.parallelStream().forEach(c -> {
            Category entity = categoryAdminMapper.toEntity(c);
            entity.setParentCategoryId(null);
            entity = categoryRepository.save(entity);
            log.info("Parent Id: {}", entity.getId());
            saveChildren(c, entity);
        });
    }

    private List<Category> saveChildren(CategoryAdminDTO c, Category parent) {
        if (c.getChildren() == null || c.getChildren().isEmpty() || parent.getId() == null) return null;
        List<Category> children = categoryAdminMapper.toEntity(c.getChildren());
        children.forEach(e -> e.setParent(parent));
        return categoryRepository.saveAllAndFlush(children);
    }

    private List<Long> toIdList(List<Category> categories) {
        return categories.stream().map(Category::getId).toList();
    }
}