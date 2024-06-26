package com.me2.rest.admin;

import com.me2.global.response.Paginate;
import com.me2.rest.admin.vm.ProductAdminVM;
import com.me2.service.ProductService;
import com.me2.service.dto.admin.ProductAdminDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
@Slf4j
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/product")
    public ResponseEntity<Void> create(@RequestBody @Valid ProductAdminDTO productAdminDTO) {
      log.debug("REST to create product");
      productService.save(productAdminDTO);
      return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/product")
    public ResponseEntity<?> update(@RequestBody @Valid ProductAdminDTO productAdminDTO) {
        log.debug("REST to update product");
        return ResponseEntity.ok(productService.save(productAdminDTO));
    }

    @GetMapping("/admin/product/{id}")
    public ResponseEntity<ProductAdminVM> getOneProduct(@PathVariable Long id) {
        log.debug("REST to get product by id: {}", id);
        return ResponseEntity.ok(productService.getProductByIdForAdmin(id));
    }

    @GetMapping({"/admin/product/list", "/public/product/list"})
    public ResponseEntity<Paginate<ProductAdminVM>> getListProduct(Pageable pageable) {
        log.debug("REST to get all products");
        return ResponseEntity.ok(productService.getAllProduct(pageable));
    }


}