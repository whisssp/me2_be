package com.me2.service.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String description;

    private ActionStatus status;

    @NotNull
    private BigDecimal price;

    @JsonProperty("variants")
    private Set<ProductVariantAdminDTO> productVariants;

}