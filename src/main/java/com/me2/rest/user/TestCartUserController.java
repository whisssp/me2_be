package com.me2.rest.user;

import com.me2.entity.Cart;
import com.me2.service.CartService;
import com.me2.service.dto.CartUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@Slf4j
public class TestCartUserController {
    @Autowired
    CartService cartService;

    @PostMapping("/user/test_cart")
    public ResponseEntity<Cart> create(CartUserDTO cartUserDTO) {
        log.debug("Rest to get one user by admin - user id: {}", cartUserDTO.getUserId());
        cartService.createCart(cartUserDTO);
        return ResponseEntity.noContent().build();
    }

}
