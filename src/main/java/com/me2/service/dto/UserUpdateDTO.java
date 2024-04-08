package com.me2.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class UserUpdateDTO {

    @NotNull
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;

    private String avatar;

    private boolean emailValidated;

}