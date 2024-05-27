package com.me2.rest.admin.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.me2.global.enums.ActionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant lastModifiedDate;

}