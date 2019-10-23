package com.egswebapp.egsweb.enums;

import org.springframework.http.HttpStatus;

public enum AuthErrorCode implements ErrorCode {

    AUTH_INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, ""),

    EMAIL_INVALID_CREDENTIALS(HttpStatus.CONFLICT, "Email already exist"),

    USER_DONT_EXIST(HttpStatus.NOT_FOUND, "user not found"),

    USER_PROFILE_NOT_ACTIVE(HttpStatus.LOCKED, "user profile dont active"),

    CHECK_CONFIRM_PASSWORD(HttpStatus.BAD_REQUEST, "confirm password is not same"),



  ;


    private HttpStatus httpStatus;
    private String message;

    AuthErrorCode(HttpStatus httpStatus, String message) {
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
