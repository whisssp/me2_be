package com.me2.service.mapper.admin;

import com.me2.entity.Promotion;
import com.me2.service.dto.admin.PromotionAdminDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PromotionAdminMapper extends EntityMapper<PromotionAdminDTO, Promotion> {
}