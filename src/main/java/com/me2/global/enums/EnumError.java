package com.me2.global.enums;

public enum EnumError {

    // User
    USER_NOT_FOUND("users", "NotFound", ""),

    USER_EXIST("users", "Exist", ""),

    USER_REGISTER_FAILED("users", "RegisterFailed", "Register failed"),

    // Media - Cloud
    UPLOAD_MEDIA_FAILED("media", "UploadFailed", ""),

    // Category
    CATEGORY_NOT_FOUND("categories", "NotFound", ""),

    // Promotion
    PROMOTION_NOT_FOUND("promotions", "NotFound", ""),

    PROMOTION_CODE_EXIST("promotions", "Exist", "Code existed"),

    // Product
    PRODUCT_NOT_FOUND("products", "NotFound", ""),

    PRODUCT_EXIST("products", "Exist", "Product existed"),

    PRODUCT_ACTIVATED_FAILED("products", "ActiveFailed", "Product must be approved"),

    PRODUCT_APPROVAL_FAILED("products", "ApproveFailed", "Product has been already deleted"),


    ;

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