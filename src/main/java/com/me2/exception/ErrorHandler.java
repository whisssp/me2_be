package com.me2.exception;

import com.me2.global.enums.EnumError;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class ErrorHandler extends RuntimeException implements Serializable {

    private EnumError error;

    public ErrorHandler(EnumError error) {
        super(error.getMessage());
        this.error = error;
    }

}