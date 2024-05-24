package com.me2.service.impl;

import com.me2.entity.Cart;
import com.me2.entity.User;
import com.me2.repository.CartRepository;
import com.me2.repository.UserRepository;
import com.me2.service.CartService;
import com.me2.service.dto.CartUserDTO;
import com.me2.service.mapper.CartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    final CartRepository cartRepository;
    final UserRepository userRepository;
    final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartMapper = cartMapper;
    }
    @Override
    public void createCart(CartUserDTO dto) {
        User u = userRepository.findById(dto.getUserId()).get();
        Cart cart = cartMapper.toEntity(dto);
        cart.setUser(u);
        cartRepository.save(cart);
    }
}