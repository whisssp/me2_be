package com.me2.service.dto.admin;

import com.me2.entity.ProductGalleryEntity;
import com.me2.entity.ProductVariantEntity;
import com.me2.global.enums.ActionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductAdminDTO {

    private Long id;

    private Long categoryId;

    private Long promotionId;

    @NotNull
    private String name;

    private String summary;

    private String poster;

    private ActionStatus status;

    @NotNull
    private BigDecimal price;

    private Set<ProductVariantAdminDTO> variants;

}