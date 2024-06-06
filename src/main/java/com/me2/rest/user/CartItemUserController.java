package com.me2.rest.user;

import com.me2.rest.user.vm.CartItemUserVM;
import com.me2.rest.user.vm.CartUserVM;
import com.me2.service.CartItemService;
import com.me2.service.CartService;
import com.me2.service.dto.user.CartItemUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/customer")
@Slf4j
public class CartItemUserController {
    private final CartItemService cartItemService;
    private final CartService cartService;

    public CartItemUserController(CartItemService cartItemService, CartService cartService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
    }

    @PostMapping("/cart/add")
    public ResponseEntity<CartItemUserVM> addCartItem(@RequestBody CartItemUserDTO dto) {
        return ResponseEntity.ok(cartItemService.save(dto));
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartUserVM> getCartItemForCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartItemForCart(id));
    }
}
