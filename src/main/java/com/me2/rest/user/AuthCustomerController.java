package com.me2.rest.user;

import com.me2.global.enums.EnumUserRole;
import com.me2.rest.common.vm.LoginVM;
import com.me2.rest.common.vm.UserEntityVM;
import com.me2.service.AuthService;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.NewPasswordDTO;
import com.me2.service.dto.ResetPasswordDTO;
import com.me2.service.dto.UserDTO;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok(authService.register(userDTO, EnumUserRole.USER));
    }

    @PostMapping("/test")
    public ResponseEntity<String> test() {
        log.debug("Rest to login user account");

        return ResponseEntity.ok("Login");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String email) throws MessagingException {
        log.debug("Rest to forgot-password email");

        return ResponseEntity.ok(authService.forgotPassword(email));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody NewPasswordDTO dto) {
        log.debug("Rest to reset-password email");

        return ResponseEntity.ok(authService.resetPassword(dto));
    }

}