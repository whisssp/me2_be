package com.me2.service.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.util.JsonConverter;
import lombok.Data;

import java.util.List;

@Data
public class ProductGalleryAdminDTO {

    private Long id;

    @JsonProperty("variantId")
    private Long productVariantId;

    @JsonProperty("images")
    private List<Object> imageList;

    @JsonIgnore
    private String images;

    public void setImages(List<Object> imageList) {
        if (imageList == null || imageList.isEmpty()) {
            this.images = null;
        }
        this.images = JsonConverter.toJson(imageList);
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        if (imageList != null && !imageList.isEmpty()) {
            return JsonConverter.toJson(imageList);
        }
        return null;
    }
}