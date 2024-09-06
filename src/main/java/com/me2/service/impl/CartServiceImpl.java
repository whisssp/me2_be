package com.me2.service.impl;

import com.me2.entity.Cart;
import com.me2.exception.CustomException;
import com.me2.global.enums.EnumError;
import com.me2.repository.CartRepository;
import com.me2.rest.user.mapper.CartUserVMMapper;
import com.me2.rest.user.vm.CartUserVM;
import com.me2.rest.user.vm.ProductVariantUserVM;
import com.me2.service.CartItemService;
import com.me2.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartUserVMMapper cartUserVMMapper;



    public CartServiceImpl(CartRepository cartRepository, CartUserVMMapper cartUserVMMapper, CartItemUserVMMapper cartItemUserVMMapper,
                           ProductVariantService productVariantService, ProductVariantUserVMMapper productVariantUserVMMapper) {
        this.cartRepository = cartRepository;
        this.cartUserVMMapper = cartUserVMMapper;
    }

    @Override
    public void saveForUser(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public CartUserVM getCartItemForCart(Long id) {
        return cartUserVMMapper.toDto(cartRepository.findById(id).orElseThrow(()-> new CustomException(EnumError.CART_NOT_FOUND)));
    }
}
