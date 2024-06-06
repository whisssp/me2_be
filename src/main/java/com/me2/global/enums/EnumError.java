package com.me2.global.enums;

public enum EnumError {

    USER_NOT_FOUND("users", "NotFound", ""),

    USER_EXIST("users", "Exist", ""),

    UPLOAD_MEDIA_FAILED("media", "UploadFailed", ""),

    CATEGORY_NOT_FOUND("categories", "NotFound", ""),

    PROMOTION_NOT_FOUND("promotions", "NotFound", ""),

    PROMOTION_CODE_EXIST("promotions", "Exist", "Code existed"),

    PRODUCT_NOT_FOUND("products", "NotFound", ""),

    PRODUCT_EXIST("products", "Exist", "Product existed"),
    USER_REGISTER_FAILED("users", "RegisterFailed", "Register failed"),
    PRODUCT_VARIANT_ID_NOT_FOUND("product_variants", "NotFound", "Id not found"),
    CART_NOT_FOUND("carts", "Not Found", "Cart not found"),
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