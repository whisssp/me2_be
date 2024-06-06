package com.me2.service;

import com.me2.entity.CartItem;
import com.me2.rest.user.vm.CartItemUserVM;
import com.me2.service.dto.user.CartItemUserDTO;

public interface CartItemService {
    CartItemUserVM save(CartItemUserDTO dto);

    CartItem findCartItemById(Long id);
}
