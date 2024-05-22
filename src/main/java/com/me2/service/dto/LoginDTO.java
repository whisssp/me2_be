package com.me2.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {

    private String username;
    private String password;
    private Boolean rememberMe;
}