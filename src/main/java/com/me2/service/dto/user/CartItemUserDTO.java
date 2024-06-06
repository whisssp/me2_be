package com.me2.service.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemUserDTO {
    @NotNull
    private Long cartId;
    @NotNull
    private Long pvId;
    private int quantity;
    private BigDecimal price;
}
