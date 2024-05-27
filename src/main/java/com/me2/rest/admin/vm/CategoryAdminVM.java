package com.me2.rest.admin.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryAdminVM {

    private Long id;

    private String name;

    private Long parentId;

    private String slug;

    private String description;

    private List<CategoryAdminVM> children;
}