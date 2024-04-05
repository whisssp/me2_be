package com.me2.rest.admin;

import com.me2.rest.vm.UserEntityVM;
import com.me2.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0/admin")
public class UserAdminController {

    private final UserService service;

    public UserAdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getOneUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntityVM>> getAllUser(Pageable pageable) {
        return ResponseEntity.ok(service.getAllUsers(pageable).getContent());
    }
}