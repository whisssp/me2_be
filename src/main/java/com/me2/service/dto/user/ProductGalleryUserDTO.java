package com.me2.service.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.util.JsonConverter;
import lombok.Data;

import java.util.List;

@Data
public class ProductGalleryUserDTO {

    private Long id;

    @JsonProperty("variantId")
    private Long productVariantId;

    @JsonProperty("images")
    private List<Object> imageList;

    @JsonIgnore
    private String images;

    public void setImageList(List<Object> images) {
        this.imageList = images;
        this.images = JsonConverter.toJson(images);
    }

    public void setImages(String images) {
        this.images = images;
        this.imageList = JsonConverter.toObjectArray(images);
    }
}
