package com.me2.web.vm;

import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class UserEntityVM {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String avatar;
    private Instant lastLogin;

}