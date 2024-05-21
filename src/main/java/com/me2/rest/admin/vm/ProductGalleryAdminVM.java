package com.me2.rest.admin.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.me2.util.JsonConverter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductGalleryAdminVM {

    private Long id;

    private Long productVariantId;

    @JsonIgnore
    private String images;

    @JsonProperty("images")
    private List<Object> imageList;

    public void setImages(String images) {
        if (images == null)
            imageList = new ArrayList<>();
        this.images = images;
        imageList = JsonConverter.toObjectArray(images);
    }

//    public void setImageList(List<Object> imageList) {
//        if (imageList == null || imageList.isEmpty())
//            this.images = null;
//        this.images = JsonConverter.toJson(imageList);
//    }
}