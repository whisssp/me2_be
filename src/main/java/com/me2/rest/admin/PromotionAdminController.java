package com.me2.rest.admin;

import com.me2.rest.admin.vm.PromotionAdminVM;
import com.me2.global.response.Paginate;
import com.me2.service.PromotionService;
import com.me2.service.dto.admin.PromotionAdminDTO;
import com.me2.service.dto.admin.filter.PromotionAdminFilterDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
@Slf4j
public class PromotionAdminController {

    private final PromotionService promotionService;

    public PromotionAdminController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/admin/promotion")
    public ResponseEntity<Void> createPromotion(@RequestBody @Valid PromotionAdminDTO promotionDTO) {
      log.debug("REST to create promotion");
      promotionService.createForAdmin(promotionDTO);
      return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/promotion")
    public ResponseEntity<PromotionAdminVM> updatePromotion(@RequestBody @Valid PromotionAdminDTO dto) {
        log.debug("REST to update promotion - id: {}", dto.getId());
        return ResponseEntity.ok(promotionService.updateForAdmin(dto));
    }

    @GetMapping("/admin/promotion/{id}")
    public ResponseEntity<PromotionAdminVM> getPromotionById(@PathVariable("id") Long id) {
        log.debug("REST to get promotion - id: {}", id);
        return ResponseEntity.ok(promotionService.getPromotionByIdForAdmin(id));
    }

    @GetMapping({"/admin/promotion/list", "/public/promotion/list"})
    public ResponseEntity<Paginate<PromotionAdminVM>> getPromotionById(PromotionAdminFilterDTO filters, Pageable pageable) {
        log.debug("REST to get promotion with filter");
        return ResponseEntity.ok(promotionService.getPromotionWithFilterForAdmin(filters, pageable));
    }

    @DeleteMapping("/admin/promotion")
    public ResponseEntity<Void> getPromotionById(@RequestBody List<Long> ids) {
        log.debug("REST to delete promotion");
        promotionService.deleteForAdmin(ids);
        return ResponseEntity.noContent().build();
    }
}