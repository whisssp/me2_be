package com.me2.service.mapper.user;

import com.me2.entity.CartItem;
import com.me2.service.dto.user.CartItemUserDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CartItemUserMapper extends EntityMapper<CartItemUserDTO, CartItem> {
}
