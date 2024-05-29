package com.me2.service.mapper.user;


import com.me2.entity.Cart;
import com.me2.service.dto.user.CartUserDTO;
import com.me2.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CartUserMapper extends EntityMapper<CartUserDTO, Cart> {
}
