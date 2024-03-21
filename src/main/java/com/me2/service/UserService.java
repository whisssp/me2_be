package com.me2.service;

import com.me2.entity.UserEntity;
import com.me2.enums.EnumUserRole;
import com.me2.service.dto.UserDTO;
import com.me2.controller.vm.UserEntityVM;
import org.springframework.data.domain.Page;

public interface UserService {


    UserEntityVM save(UserDTO userDTO, EnumUserRole role);

    UserEntityVM update(UserDTO info);

    void delete(Long id);

    UserEntityVM getOneUserById(Long id);

    Page<UserEntityVM> getAllUsers();
}