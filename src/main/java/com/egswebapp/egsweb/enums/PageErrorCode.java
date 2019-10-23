package com.egswebapp.egsweb.enums;

import org.springframework.http.HttpStatus;

public enum PageErrorCode implements ErrorCode {
    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "page not found");

    PageErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private HttpStatus httpStatus;

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
