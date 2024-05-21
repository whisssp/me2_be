package com.me2.rest.admin;

import com.me2.global.response.Paginate;
import com.me2.rest.vm.UserEntityVM;
import com.me2.service.UserService;
import com.me2.service.dto.UserUpdateDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/admin")
@Slf4j
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntityVM> getUserById(@PathVariable("id") Long id) {
        log.debug("Rest to get one user by admin - user id: {}", id);
        return ResponseEntity.ok(userService.getOneUserById(id));
    }

    @PutMapping("/user")
    public ResponseEntity<UserEntityVM> updateUserById(@Valid @RequestBody UserUpdateDTO userDTO) {
        log.debug("Rest to update user info by admin");
        return ResponseEntity.ok(userService.update(userDTO));
    }

    @GetMapping({"/users", "/user/list"})
    public ResponseEntity<Paginate<UserEntityVM>> getAllUser(Pageable pageable) {
        log.debug("Rest to get all user by admin");
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        log.debug("Rest to delete user by admin - user id: {}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}