package com.me2.util;

import com.me2.rest.response.Paginate;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageUtil<T> {

    public Paginate<T> toPaginateResponse(Page<T> page) {
        Paginate<T> response = new Paginate<>();

        if (!page.isEmpty()) {
            response.setData(page.getContent());
        } else {
            response.setData(new ArrayList<>());
        }
        response.setPageSize(page.getSize());
        response.setPageNumber(page.getNumber());
        response.setTotalElements(Integer.parseInt(page.getTotalElements() + ""));
        response.setTotalPages(Integer.parseInt(page.getTotalPages() + ""));

        return response;
    }

}