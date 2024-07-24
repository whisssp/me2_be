package com.me2.service.impl;

import com.me2.entity.Cart;
import com.me2.entity.CartItem;
import com.me2.entity.ProductVariant;
import com.me2.exception.CustomException;
import com.me2.global.enums.EnumCartItemStatus;
import com.me2.global.enums.EnumError;
import com.me2.repository.CartItemRepository;
import com.me2.repository.CartRepository;
import com.me2.rest.user.mapper.CartItemUserVMMapper;
import com.me2.rest.user.mapper.ProductVariantUserVMMapper;
import com.me2.rest.user.vm.CartItemUserVM;
import com.me2.service.CartAndCartItemService;
import com.me2.service.CartItemService;
import com.me2.service.CartService;
import com.me2.service.ProductVariantService;
import com.me2.service.dto.user.CartItemUserDTO;
import com.me2.service.mapper.user.CartItemUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CartItemServiceImpl implements CartItemService {
    private final CartItemUserMapper cartItemUserMapper;
    private final CartItemUserVMMapper cartItemUserVMMapper;
    private final ProductVariantService productVariantService;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantUserVMMapper productVariantUserVMMapper;
    private final CartAndCartItemService cartAndCartItemService;
    private final CartRepository cartRepository;


    public CartItemServiceImpl(CartItemUserMapper cartItemUserMapper, CartItemUserVMMapper cartItemUserVMMapper,
                               ProductVariantService productVariantService, CartItemRepository cartItemRepository,
                               ProductVariantUserVMMapper productVariantUserVMMapper, CartAndCartItemService cartAndCartItemService, CartRepository cartRepository) {
        this.cartItemUserMapper = cartItemUserMapper;
        this.cartItemUserVMMapper = cartItemUserVMMapper;
        this.productVariantService = productVariantService;
        this.cartItemRepository = cartItemRepository;
        this.productVariantUserVMMapper = productVariantUserVMMapper;
        this.cartAndCartItemService = cartAndCartItemService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItemUserVM save(CartItemUserDTO dto) {
        if(dto.getCartId() == null) throw new CustomException(EnumError.CART_NOT_FOUND);
        return saveCartItem(savePV(dto.getPvId()), dto);
    }

    @Override
    public CartItem findCartItemById(Long id) {
        return cartItemRepository.findById(id).get();
    }

    private ProductVariant savePV(Long id) {
        return productVariantService.savePVForCartItem(id);
    }

    private CartItemUserVM saveCartItem(ProductVariant productVariant, CartItemUserDTO dto) {
        productVariant.getCartItems().forEach(cartItem -> {
            if(cartItem.getCartId() == dto.getCartId() && Objects.equals(cartItem.getProductVariant().getId(), productVariant.getId()))
                throw new CustomException(EnumError.CART_ITEM_ALREADY_EXIST);
        });
        CartItem cartItem = cartItemUserMapper.toEntity(dto);
        cartItem.setProductVariant(productVariant);
        cartItem.setPrice(productVariant.getProduct().getPrice());
        CartItemUserVM newCartItem = cartItemUserVMMapper.toDto(cartItemRepository.save(cartItem));
        newCartItem.setProductVariant(productVariantUserVMMapper.toDto(productVariant));
        return newCartItem;
    }
}
