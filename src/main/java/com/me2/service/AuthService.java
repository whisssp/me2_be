package com.me2.service;

import com.me2.rest.common.vm.LoginVM;
import com.me2.rest.common.vm.UserEntityVM;
import com.me2.global.enums.EnumUserRole;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.UserDTO;

public interface AuthService {


    LoginVM login(LoginDTO loginDTO);

    UserEntityVM register(UserDTO userDTO, EnumUserRole role);
}