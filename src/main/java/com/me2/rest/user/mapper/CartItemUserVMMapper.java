package com.me2.rest.user.mapper;

import com.me2.entity.CartItem;
import com.me2.rest.user.vm.CartItemUserVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductVariantUserVMMapper.class})
public interface CartItemUserVMMapper extends EntityMapper<CartItemUserVM, CartItem> {
}
