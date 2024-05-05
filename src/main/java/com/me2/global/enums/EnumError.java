package com.me2.global.enums;

public enum EnumError {

    USER_NOT_FOUND("users", "NotFound", ""),

    USER_EXIST("users", "Exist", ""),

    UPLOAD_MEDIA_FAILED("media", "UploadFailed", ""),

    CATEGORY_NOT_FOUND("categories", "NotFound", "");

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