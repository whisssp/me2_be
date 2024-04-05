package com.me2.service;

import com.me2.global.enums.EnumUserRole;
import com.me2.service.dto.UserDTO;
import com.me2.rest.vm.UserEntityVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {


    UserEntityVM save(UserDTO userDTO, EnumUserRole role);

    UserEntityVM update(UserDTO info);

    void delete(Long id);

    UserEntityVM getOneUserById(Long id);

    Page<UserEntityVM> getAllUsers(Pageable pageable);
}