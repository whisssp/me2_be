package com.me2.service;

import com.me2.controller.vm.LoginVM;
import com.me2.service.dto.LoginDTO;

public interface AuthService {


    LoginVM login(LoginDTO loginDTO);
}