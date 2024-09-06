package com.me2.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.service.dto.admin.ProductVariantAdminDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartItemUserDTO {
    private Long id;

    private Long cartId;

    private int quantity;

    private BigDecimal price;

    @JsonProperty("variant")
    private ProductVariantUserDTO productVariant;
}
