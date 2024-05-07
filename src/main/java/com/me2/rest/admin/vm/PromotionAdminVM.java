package com.me2.rest.admin.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.me2.global.enums.EnumPromotionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PromotionAdminVM {

    private Long id;

    private EnumPromotionType type;

    private BigDecimal value;

    private Instant fromDate;

    private Instant toDate;

    private Boolean isActivated;

    private String code;

    private String name;

    private String content;

    private Integer quantity;

    private Integer quantityUsed;

    private Instant createdDate;

    private Instant lastModifiedDate;
}