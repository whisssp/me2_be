package com.me2.service.dto;

import lombok.Data;

@Data
public class NewPasswordDTO {
    private String email;
    private String code;
    private String newPassword;
}
