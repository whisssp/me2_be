package com.me2.rest.admin.mapper;

import com.me2.entity.Cart;
import com.me2.rest.admin.vm.CartAdminVM;
import com.me2.service.dto.admin.CartAdminDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CartAdminVMMapper extends EntityMapper<CartAdminVM, Cart> {
}