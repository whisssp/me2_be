package com.me2.rest.admin;

import com.me2.entity.CategorieEntity;
import com.me2.rest.response.Paginate;
import com.me2.rest.vm.CategoryVM;
import com.me2.service.CategoryService;
import com.me2.service.dto.CategoryDTO;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/admin")
@Slf4j
public class CategoryAdminController {

    private final CategoryService categoryService;

    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<Void> createCategory(@RequestBody @Valid List<CategoryDTO> listCategoryDTO) {
        log.debug("REST to create categories");
        categoryService.create(listCategoryDTO);
        return ResponseEntity.noContent().build();
    }
  
    @PutMapping("/category")
    public ResponseEntity<CategoryVM> update(@RequestBody @Valid CategoryDTO categoryDTO) {
        log.debug("REST to update categories");
        return ResponseEntity.ok(categoryService.update(categoryDTO));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryVM> getOne(@PathVariable("id") Long id) {
        log.debug("REST to get category by id: {}", id);
        return ResponseEntity.ok(categoryService.getOneById(id));
    }

    @GetMapping("/category")
    public ResponseEntity<Paginate<CategoryVM>> update(Pageable pageable) {
        log.debug("REST to get all categories");
        return ResponseEntity.ok(categoryService.getAll(pageable));
    }

    @DeleteMapping("/category")
    public ResponseEntity<Void> delete(@RequestBody List<Long> ids) {
        log.debug("REST to delete categories");
        categoryService.delete(ids);
        return ResponseEntity.noContent().build();
    }
}