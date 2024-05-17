package com.me2.rest.vm;

import com.me2.global.enums.ActionStatus;
import com.me2.rest.admin.vm.ProductVariantAdminVM;
import com.me2.service.dto.admin.ProductVariantAdminDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
public class ProductVM {

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

    private List<ProductVariantAdminVM> variants;

}