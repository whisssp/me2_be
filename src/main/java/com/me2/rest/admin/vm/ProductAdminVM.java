package com.me2.rest.admin.vm;

import com.me2.global.enums.ActionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductAdminVM {

    private Long id;

    private CategoryAdminVM category;

    private PromotionAdminVM promotion;

    @NotNull
    private String name;

    private String summary;

    private String poster;

    private ActionStatus status;

    private String description;

    @NotNull
    private BigDecimal price;

    private List<ProductVariantAdminVM> productVariants;

}