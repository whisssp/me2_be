package com.me2.service;

import com.me2.service.dto.admin.ProductGalleryAdminDTO;

import java.util.List;

public interface ProductGalleryService {

    void save(List<ProductGalleryAdminDTO> galleryAdminDTO);
}