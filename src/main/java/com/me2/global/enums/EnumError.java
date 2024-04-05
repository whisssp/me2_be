package com.me2.global.enums;

public enum EnumError {

    USER_NOT_FOUND("users", "NotFound", ""),

    USER_EXIST("users", "Exist", "");

    private String entity;

    private String errorKey;

    private String message;

     EnumError(String entity, String errorKey, String message) {
         this.entity = entity;
         this.errorKey = errorKey;
         this.message = message;
     }

    public String getEntity() {
        return entity;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getMessage() {
        return message;
    }
}