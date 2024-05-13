package com.me2.rest.admin.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.global.enums.EnumPromotionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PromotionAdminVM {

    private Long id;

    private EnumPromotionType type;

    private BigDecimal value;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant endDate;

    private Boolean isActivated;

    private String code;

    private String name;

    private String content;

    private Integer quantity;

    private Integer quantityUsed;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant lastModifiedDate;

    private String image;
}