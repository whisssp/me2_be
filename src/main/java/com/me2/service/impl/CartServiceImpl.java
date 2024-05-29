package com.me2.service.impl;

import com.me2.entity.Cart;
import com.me2.repository.CartRepository;
import com.me2.repository.UserRepository;
import com.me2.service.CartService;
import com.me2.service.UserService;
import com.me2.service.dto.user.CartUserDTO;
import com.me2.service.mapper.user.CartUserMapper;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private final CartUserMapper cartUserMapper;


    public CartServiceImpl(CartRepository cartRepository, CartUserMapper cartUserMapper) {
        this.cartRepository = cartRepository;
        this.cartUserMapper = cartUserMapper;
    }

    @Override
    public void saveForUser(Cart cart) {
        cartRepository.save(cart);
    }
}
