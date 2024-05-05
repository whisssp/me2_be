package com.me2.rest.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVM {

    private Long id;

    private String name;

    private Long parentCategoryId;

    private String slug;

    private String description;

    @JsonProperty("children")
    private List<CategoryVM> children;
}