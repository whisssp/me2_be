package com.me2.service.impl;

import com.me2.entity.CartItem;
import com.me2.entity.ProductVariant;
import com.me2.exception.CustomException;
import com.me2.global.enums.EnumError;
import com.me2.repository.CartItemRepository;
import com.me2.service.CartItemService;
import com.me2.service.ProductVariantService;
import com.me2.service.dto.user.CartItemUserDTO;
import com.me2.service.mapper.user.CartItemUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Slf4j
public class CartItemServiceImpl implements CartItemService {
    private final CartItemUserMapper cartItemUserMapper;
    private final ProductVariantService productVariantService;
    private final CartItemRepository cartItemRepository;


    public CartItemServiceImpl(CartItemUserMapper cartItemUserMapper, ProductVariantService productVariantService, CartItemRepository cartItemRepository) {
        this.cartItemUserMapper = cartItemUserMapper;
        this.productVariantService = productVariantService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public CartItem save(CartItemUserDTO dto) {
        CartItem entity = null;
        if(dto.getId() == null) {
            entity = saveCartItem(dto);
        } else {
            entity = cartItemRepository.findById(dto.getId()).
                    orElseThrow(() -> new CustomException(EnumError.CART_ITEM_NOT_FOUND));

            initializeProxy(entity);

            cartItemUserMapper.partialUpdate(entity, dto);

            entity = cartItemRepository.save(entity);
        }
        return entity;
    }

    @Override
    public CartItem findCartItemById(Long id) {
        return cartItemRepository.findById(id).get();
    }

    private ProductVariant savePV(Long id) {
        return productVariantService.savePVForCartItem(id);
    }

    private CartItem saveCartItem(CartItemUserDTO dto) {
        if(dto.getCartId() == null) throw new CustomException(EnumError.CART_NOT_FOUND);
        CartItem cartItem = cartItemUserMapper.toEntity(dto);
        return cartItemRepository.saveAndFlush(cartItem);
    }
    private void initializeProxy(CartItem entity) {
        if (entity instanceof HibernateProxy) {
            Hibernate.initialize(entity.getProductVariant());
        }
    }

}
