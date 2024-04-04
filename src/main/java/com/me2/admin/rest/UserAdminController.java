package com.me2.admin.rest;

import com.me2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/admin")
public class UserAdminController {

    private final UserService service;

    public UserAdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getOneUserById(id));
    }
}