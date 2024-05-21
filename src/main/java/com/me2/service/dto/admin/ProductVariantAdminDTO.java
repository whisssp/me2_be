package com.me2.service.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.entity.Product;
import com.me2.entity.ProductGallery;
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

    private Product product;

    @JsonProperty("galleries")
    private List<ProductGalleryAdminDTO> productGalleries;

}