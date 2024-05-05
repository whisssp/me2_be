package com.me2.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private Long id;

    private Long parentCategoryId;

    private String slug;

    @NotNull
    private String name;

    private String description;

    @JsonProperty("children")
    private List<CategoryDTO> children;
}