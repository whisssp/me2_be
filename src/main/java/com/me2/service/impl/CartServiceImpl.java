package com.me2.service.impl;

import com.me2.entity.Cart;
import com.me2.entity.CartItem;
import com.me2.exception.CustomException;
import com.me2.global.enums.EnumError;
import com.me2.repository.CartRepository;
import com.me2.rest.user.mapper.CartItemUserVMMapper;
import com.me2.rest.user.mapper.CartUserVMMapper;
import com.me2.rest.user.mapper.ProductVariantUserVMMapper;
import com.me2.rest.user.vm.CartItemUserVM;
import com.me2.rest.user.vm.CartUserVM;
import com.me2.rest.user.vm.ProductVariantUserVM;
import com.me2.service.CartItemService;
import com.me2.service.CartService;
import com.me2.service.ProductVariantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final CartUserVMMapper cartUserVMMapper;
    private final CartItemUserVMMapper cartItemUserVMMapper;
    private final ProductVariantService productVariantService;
    private final ProductVariantUserVMMapper productVariantUserVMMapper;



    public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService, CartUserVMMapper cartUserVMMapper, CartItemUserVMMapper cartItemUserVMMapper, ProductVariantService productVariantService, ProductVariantUserVMMapper productVariantUserVMMapper) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.cartUserVMMapper = cartUserVMMapper;
        this.cartItemUserVMMapper = cartItemUserVMMapper;
        this.productVariantService = productVariantService;
        this.productVariantUserVMMapper = productVariantUserVMMapper;
    }

    @Override
    public void saveForUser(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public CartUserVM getCartItemForCart(Long id) {
        Optional<Cart> findCart = cartRepository.findById(id);
        if(findCart.isEmpty()) throw new CustomException(EnumError.CART_NOT_FOUND);

        List<CartItemUserVM> cartItem = cartItemUserVMMapper.toDto(findCart.get().getCartItems());
        cartItem.forEach((cart) -> {
            CartItem c = cartItemService.findCartItemById(cart.getId());
            ProductVariantUserVM productVariantUserVM = productVariantUserVMMapper.toDto(productVariantService.savePVforCartItem(c.getProductVariant().getId()));
            cart.setVariantUserVM(productVariantUserVM);
        });

        CartUserVM cart = cartUserVMMapper.toDto(findCart.get());
        cart.setCartItem(cartItem);

        return cart;
    }
}
