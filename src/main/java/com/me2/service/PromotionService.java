package com.me2.service;

import com.me2.rest.admin.vm.PromotionAdminVM;
import com.me2.global.response.Paginate;
import com.me2.service.dto.admin.PromotionAdminDTO;
import com.me2.service.dto.admin.filter.PromotionAdminFilterDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromotionService {

    void createForAdmin(PromotionAdminDTO dto);

    PromotionAdminVM updateForAdmin(PromotionAdminDTO dto);

    Paginate<PromotionAdminVM> getPromotionWithFilterForAdmin(PromotionAdminFilterDTO filters, Pageable pageable);

    PromotionAdminVM getPromotionByIdForAdmin(Long id);

    void deleteForAdmin(List<Long> ids);
}