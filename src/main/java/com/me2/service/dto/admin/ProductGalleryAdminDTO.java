package com.me2.service.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.util.JsonConverter;
import lombok.Data;

import java.util.ArrayList;
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

    public void setImageList(List<Object> images) {
//        if (images == null || images.isEmpty()) {
//            this.images = null;
//        }
        this.imageList = images;
        this.images = JsonConverter.toJson(images);
    }

    public void setImages(String images) {
        this.images = images;
        this.imageList = JsonConverter.toObjectArray(images);
//        if (images == null || images.isEmpty()) {
//            this.imageList = new ArrayList<>();
//        }
    }
}