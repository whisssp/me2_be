package com.me2.customer.rest;

import com.me2.web.vm.LoginVM;
import com.me2.web.vm.UserEntityVM;
import com.me2.service.AuthService;
import com.me2.web.dto.LoginDTO;
import com.me2.service.dto.UserDTO;
import jakarta.validation.Valid;
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
public class AuthCustomerController {



    @Autowired
    private final AuthService authService;

    public AuthCustomerController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginVM> doLogin(@RequestBody LoginDTO loginDTO) {
        log.info("Rest to login with: {}", loginDTO.toString());
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntityVM> register(@Valid @RequestBody UserDTO userDTO) {
        log.debug("Rest to register user account");

        return ResponseEntity.ok(authService.register(userDTO, null));
    }

    @PostMapping("/test")
    public ResponseEntity<String> test() {
        log.debug("Rest to login user account");

        return ResponseEntity.ok("Login");
    }
}