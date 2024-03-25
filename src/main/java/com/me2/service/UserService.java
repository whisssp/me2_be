package com.me2.service;

import com.me2.global.enums.EnumUserRole;
import com.me2.service.dto.UserDTO;
import com.me2.web.vm.UserEntityVM;
import org.springframework.data.domain.Page;

public interface UserService {


    UserEntityVM save(UserDTO userDTO, EnumUserRole role);

    UserEntityVM update(UserDTO info);

    void delete(Long id);

    UserEntityVM getOneUserById(Long id);

    Page<UserEntityVM> getAllUsers();
}