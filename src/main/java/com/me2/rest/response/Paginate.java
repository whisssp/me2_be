package com.me2.rest.response;

import lombok.Data;

import java.util.List;

@Data
public class Paginate<T> {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalElements;

    private List<T> data;
}