package com.me2.service.dto.admin;

import com.me2.global.enums.ActionStatus;
import com.me2.global.enums.EnumPromotionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PromotionAdminDTO {

    private Long id;

    @NotNull
    private EnumPromotionType type;

    @NotNull
    private BigDecimal value;

    private Instant startDate;

    private Instant endDate;

    private Boolean isActivated;

    private String code;

    @NotNull
    private String name;

    private String content;

    @NotNull
    private Integer quantity;

    private Integer quantityUsed;

    private String image;
}