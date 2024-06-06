package com.me2.rest.user;

import com.me2.rest.user.vm.CartItemUserVM;
import com.me2.service.CartItemService;
import com.me2.service.dto.user.CartItemUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/customer")
@Slf4j
public class CartItemUserController {
    private final CartItemService cartItemService;

    public CartItemUserController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/cart/add")
    public ResponseEntity<CartItemUserVM> addCartItem(@RequestBody CartItemUserDTO dto) {
        return ResponseEntity.ok(cartItemService.save(dto));
    }
}
