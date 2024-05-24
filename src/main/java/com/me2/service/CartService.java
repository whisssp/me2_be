package com.me2.service;

import com.me2.entity.Cart;
import com.me2.service.dto.CartUserDTO;
import org.springframework.stereotype.Service;

public interface CartService {
    void createCart(CartUserDTO dto);
}
