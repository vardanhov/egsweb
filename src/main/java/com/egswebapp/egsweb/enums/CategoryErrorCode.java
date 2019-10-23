package com.egswebapp.egsweb.enums;

import org.springframework.http.HttpStatus;

public enum CategoryErrorCode implements ErrorCode {

    CATEGORY_IS_EXIST(HttpStatus.BAD_REQUEST, "category name is exist"),
    NUMBER_FORMAT_IS_NOT_VALID(HttpStatus.BAD_REQUEST, "Id format is not correct"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "category noy found"),
    CATEGORY_BY_NAME_EXIST(HttpStatus.CONFLICT, "category by name already exist");


    private HttpStatus status;
    private String message;

    CategoryErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
