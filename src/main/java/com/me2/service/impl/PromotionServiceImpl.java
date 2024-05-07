package com.me2.service.impl;

import com.me2.entity.PromotionEntity;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.ActionStatus;
import com.me2.global.enums.EnumError;
import com.me2.repository.PromotionRepository;
import com.me2.rest.admin.mapper.PromotionAdminVMMapper;
import com.me2.rest.admin.vm.PromotionAdminVM;
import com.me2.global.response.Paginate;
import com.me2.service.PromotionService;
import com.me2.service.dto.admin.PromotionAdminDTO;
import com.me2.service.dto.admin.filter.PromotionAdminFilterDTO;
import com.me2.service.mapper.admin.PromotionAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    private final PromotionAdminMapper promotionAdminMapper;

    private final PromotionAdminVMMapper promotionAdminVMMapper;

    public PromotionServiceImpl(PromotionRepository promotionRepository,
                                PromotionAdminMapper promotionAdminMapper,
                                PromotionAdminVMMapper promotionAdminVMMapper) {
        this.promotionRepository = promotionRepository;
        this.promotionAdminMapper = promotionAdminMapper;
        this.promotionAdminVMMapper = promotionAdminVMMapper;
    }

    @Override
    public void createForAdmin(PromotionAdminDTO dto) {
        log.debug("Request to create promotion - promotion_code: {}", dto.getCode());
        if (promotionRepository.findPromotionEntityByCode(dto.getCode()) != null)
            throw new ErrorHandler(EnumError.PROMOTION_CODE_EXIST);
        PromotionEntity entity = promotionAdminMapper.toEntity(dto);
        entity.setStatus(ActionStatus.WAITING_FOR_APPROVAL);
        entity.setIsActivated(false);
        promotionRepository.save(entity);
    }

    @Override
    public PromotionAdminVM updateForAdmin(PromotionAdminDTO dto) {
        return null;
    }

    @Override
    public Paginate<PromotionAdminVM> getPromotionWithFilterForAdmin(PromotionAdminFilterDTO filters, Pageable pageable) {
        return null;
    }

    @Override
    public PromotionAdminVM getPromotionByIdForAdmin(Long id) {
        log.debug("Request to get promotion by id: {}", id);
        return promotionAdminVMMapper.toDto(promotionRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(EnumError.PROMOTION_NOT_FOUND)));
    }

    @Override
    public void deleteForAdmin(List<Long> ids) {

    }
}