package com.me2.rest.admin;

import com.me2.service.CategoryService;
import com.me2.service.dto.CategoryDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0/admin")
@Slf4j
public class CategoryAdminController {

    private final CategoryService categoryService;

    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<Void> createCategory(@RequestBody @Valid List<CategoryDTO> listCategoryDTO) {
        log.debug("REST to create categories");
        categoryService.create(listCategoryDTO);
        return ResponseEntity.noContent().build();
    }
}