package com.me2.rest.user.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.rest.admin.vm.ProductGalleryAdminVM;
import lombok.Data;

import java.util.List;

@Data
public class ProductVariantUserVM {
    private Long id;

    private Long productId;

    private String size;

    private String sku;

    private String name;

    @JsonProperty("galleries")
    private List<ProductGalleryAdminVM> productGalleries;
}
