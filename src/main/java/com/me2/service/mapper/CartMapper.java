package com.me2.service.mapper;

import com.me2.entity.Cart;
import com.me2.service.dto.CartUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CartMapper extends EntityMapper<CartUserDTO, Cart>{
}
