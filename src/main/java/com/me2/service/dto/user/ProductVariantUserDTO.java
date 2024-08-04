package com.me2.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.entity.Product;
import com.me2.service.dto.admin.ProductGalleryAdminDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductVariantUserDTO {

    private Long id;

    private String size;

    private String color;

    private String sku;

    private String name;

    private Product product;

    @JsonProperty("galleries")
    private List<ProductGalleryUserDTO> productGalleries;

}
