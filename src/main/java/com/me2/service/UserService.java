package com.me2.service;

import com.me2.entity.User;
import com.me2.global.enums.EnumUserRole;
import com.me2.global.response.Paginate;
import com.me2.service.dto.UserDTO;
import com.me2.rest.vm.UserEntityVM;
import com.me2.service.dto.UserUpdateDTO;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    UserEntityVM save(UserDTO userDTO, EnumUserRole role);

    UserEntityVM update(UserUpdateDTO info);

    void delete(Long id);

    UserEntityVM getOneUserById(Long id);

    Paginate<UserEntityVM> getAllUsers(Pageable pageable);
}