package com.egswebapp.egsweb.enums;

import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorCode {

    USER_OLD_PASSWORD(HttpStatus.CONFLICT, "use old password"),

    NOT_SAME_PASSWORD(HttpStatus.BAD_REQUEST, "not same password"),

    EDIT_USER_ROLE(HttpStatus.CONFLICT, "User cannot have same role"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),

    USER_PROFILE_IS_ACTIVATED(HttpStatus.OK, "user is activated"),

    ;


    private HttpStatus status;
    private String message;

    UserErrorCode(HttpStatus status, String message) {
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
