package com.me2.service.impl;

import com.me2.entity.Cart;
import com.me2.entity.CartItem;
import com.me2.entity.ProductVariant;
import com.me2.repository.CartItemRepository;
import com.me2.rest.user.mapper.CartItemUserVMMapper;
import com.me2.rest.user.mapper.ProductVariantUserVMMapper;
import com.me2.rest.user.vm.CartItemUserVM;
import com.me2.service.CartItemService;
import com.me2.service.ProductVariantService;
import com.me2.service.dto.user.CartItemUserDTO;
import com.me2.service.mapper.user.CartItemUserMapper;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemUserMapper cartItemUserMapper;
    private final CartItemUserVMMapper cartItemUserVMMapper;
    private final ProductVariantService productVariantService;
    private final CartItemRepository cartItemRepository;

    private final ProductVariantUserVMMapper productVariantUserVMMapper;

    public CartItemServiceImpl(CartItemUserMapper cartItemUserMapper, CartItemUserVMMapper cartItemUserVMMapper, ProductVariantService productVariantService, CartItemRepository cartItemRepository, ProductVariantUserVMMapper productVariantUserVMMapper) {
        this.cartItemUserMapper = cartItemUserMapper;
        this.cartItemUserVMMapper = cartItemUserVMMapper;
        this.productVariantService = productVariantService;
        this.cartItemRepository = cartItemRepository;
        this.productVariantUserVMMapper = productVariantUserVMMapper;
    }

    @Override
    public CartItemUserVM save(CartItemUserDTO dto) {
        return saveCartItem(savePV(dto.getPvId()), dto);
    }

    private ProductVariant savePV(Long id) {
        return productVariantService.savePVforCartItem(id);
    }

    private CartItemUserVM saveCartItem(ProductVariant productVariant, CartItemUserDTO dto) {
        CartItem cartItem = cartItemUserMapper.toEntity(dto);
        cartItem.setProductVariant(productVariant);
        CartItemUserVM newCartItem = cartItemUserVMMapper.toDto(cartItemRepository.save(cartItem));
        newCartItem.setVariantUserVM(productVariantUserVMMapper.toDto(productVariant));
        return newCartItem;
    }
}
