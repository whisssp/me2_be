package com.me2.service;

import com.me2.rest.common.vm.LoginVM;
import com.me2.rest.common.vm.UserEntityVM;
import com.me2.global.enums.EnumUserRole;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.NewPasswordDTO;
import com.me2.service.dto.UserDTO;
import jakarta.mail.MessagingException;

public interface AuthService {


    LoginVM login(LoginDTO loginDTO);

    UserEntityVM register(UserDTO userDTO, EnumUserRole role);

    String forgotPassword(String email) throws MessagingException;

    String resetPassword(NewPasswordDTO dto);
}