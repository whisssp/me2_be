package com.me2.rest.admin;

import com.me2.rest.vm.LoginVM;
import com.me2.rest.vm.UserEntityVM;
import com.me2.global.enums.EnumUserRole;
import com.me2.service.AuthService;
import com.me2.service.UserService;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/admin")
@Slf4j
public class AuthAdminController {

    private final UserService userService;

    private final AuthService authService;

    public AuthAdminController(UserService userService, AuthService authService) {
        this.userService = userService;
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
        return ResponseEntity.ok(authService.register(userDTO, EnumUserRole.ADMIN));
    }

    @PostMapping("/test")
    public ResponseEntity<String> test() {
        log.debug("Rest to login user account");

        return ResponseEntity.ok("Login");
    }
}