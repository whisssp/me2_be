package com.me2.controller.auth;

import com.me2.controller.vm.LoginVM;
import com.me2.controller.vm.UserEntityVM;
import com.me2.service.AuthService;
import com.me2.service.UserService;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/auth")
@Slf4j
public class AuthController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginVM> doLogin(@RequestBody LoginDTO loginDTO) {
        log.info("Rest to login with: {}", loginDTO.toString());
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntityVM> register(@Valid @RequestBody UserDTO userDTO) {
        log.debug("Rest to register user account");

        return ResponseEntity.ok(userService.create(userDTO));
    }

    @PostMapping("/test")
    public ResponseEntity<String> test() {
        log.debug("Rest to login user account");

        return ResponseEntity.ok("Login");
    }
}