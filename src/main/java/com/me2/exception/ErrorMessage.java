package com.me2.exception;

import com.me2.global.enums.EnumError;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {

    private String entity;
    private String errorKey;
    private String message;

    public ErrorMessage(EnumError error) {
        this.entity = error.getEntity();
        this.errorKey = error.getErrorKey();
        this.message = error.getMessage();
    }
}