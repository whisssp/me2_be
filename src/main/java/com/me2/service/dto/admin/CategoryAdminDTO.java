package com.me2.service.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CategoryAdminDTO {

    private Long id;

    private CategoryAdminDTO parent;

    private String slug;

    @NotNull
    private String name;

    private String description;

    @JsonProperty("children")
    private List<CategoryAdminDTO> children;
}