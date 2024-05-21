package com.me2.rest.admin;

import com.me2.global.response.Paginate;
import com.me2.rest.admin.vm.CategoryAdminVM;
import com.me2.service.CategoryService;
import com.me2.service.dto.admin.CategoryAdminDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<Void> createCategory(@RequestBody @Valid List<CategoryAdminDTO> listCategoryAdminDTO) {
        log.debug("REST to create categories");
        categoryService.create(listCategoryAdminDTO);
        return ResponseEntity.noContent().build();
    }
  
    @PutMapping("/category")
    public ResponseEntity<CategoryAdminVM> update(@RequestBody @Valid CategoryAdminDTO categoryAdminDTO) {
        log.debug("REST to update categories");
        return ResponseEntity.ok(categoryService.update(categoryAdminDTO));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryAdminVM> getOne(@PathVariable("id") Long id) {
        log.debug("REST to get category by id: {}", id);
        return ResponseEntity.ok(categoryService.getOneById(id));
    }

    @GetMapping("/category/list")
    public ResponseEntity<Paginate<CategoryAdminVM>> findAllForAdmin(Pageable pageable) {
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