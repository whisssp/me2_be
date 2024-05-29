package com.me2.service;

import com.me2.entity.Cart;
import com.me2.service.dto.user.CartUserDTO;

public interface CartService {
    void saveForUser(Cart cart);
}
