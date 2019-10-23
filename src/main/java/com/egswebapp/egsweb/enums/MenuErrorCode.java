package com.egswebapp.egsweb.enums;

import org.springframework.http.HttpStatus;

public enum MenuErrorCode implements ErrorCode {


    MENU_NOT_EXIST(HttpStatus.NOT_FOUND, "Menu not exist");



    private HttpStatus httpStatus;
    private String message;


    MenuErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
