package com.me2.rest.admin.mapper;

import com.me2.entity.Promotion;
import com.me2.rest.admin.vm.PromotionAdminVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PromotionAdminVMMapper extends EntityMapper<PromotionAdminVM, Promotion> {
}