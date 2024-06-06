package com.me2.rest.user.mapper;

import com.me2.entity.Cart;
import com.me2.rest.user.vm.CartUserVM;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CartItemUserVMMapper.class})
public interface CartUserVMMapper extends EntityMapper<CartUserVM, Cart>{
}
