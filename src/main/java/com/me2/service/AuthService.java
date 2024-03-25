package com.me2.service;

import com.me2.web.vm.LoginVM;
import com.me2.web.vm.UserEntityVM;
import com.me2.global.enums.EnumUserRole;
import com.me2.web.dto.LoginDTO;
import com.me2.service.dto.UserDTO;

public interface AuthService {


    LoginVM login(LoginDTO loginDTO);

    UserEntityVM register(UserDTO userDTO, EnumUserRole role);
}