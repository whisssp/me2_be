package com.me2.service.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;
    private String password;
    private Boolean rememberMe;
}