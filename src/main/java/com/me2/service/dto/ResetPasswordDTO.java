package com.me2.service.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String email;
    private String code;
}
