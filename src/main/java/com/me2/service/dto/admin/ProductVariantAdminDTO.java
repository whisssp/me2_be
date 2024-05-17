package com.me2.service.dto.admin;

import lombok.Data;

import java.util.List;

@Data
public class ProductVariantAdminDTO {

    private Long id;

    private Integer quantity;

    private String size;

    private String color;

    private String sku;

    private String name;

    private Long productId;

    private ProductGalleryAdminDTO gallery;

}