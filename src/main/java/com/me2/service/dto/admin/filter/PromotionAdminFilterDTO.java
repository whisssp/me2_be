package com.me2.service.dto.admin.filter;

import com.me2.global.enums.EnumPromotionType;
import lombok.Data;

import java.time.Instant;

@Data
public class PromotionAdminFilterDTO {

    private Long id;

    private EnumPromotionType type;

    private Instant fromStartDate;

    private Instant toStartDate;

    private Instant fromEndDate;

    private Instant toEndDate;

    private Boolean isActivated;

    private String code;

    private String name;

    private String content;

    private Integer fromQuantity;

    private Integer toQuantity;

    private Integer fromQuantityUsed;

    private Integer toQuantityUsed;

    private Instant fromCreatedDate;

    private Instant toCreatedDate;

    private Instant toLastModifiedDate;

    private String fromLastModifiedDate;
}