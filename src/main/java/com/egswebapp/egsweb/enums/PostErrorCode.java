package com.egswebapp.egsweb.enums;

import org.springframework.http.HttpStatus;

public enum PostErrorCode implements  ErrorCode {


    POST_NOT_EXIST(HttpStatus.NOT_FOUND, "Post not exist"),

    POST_CONTENT_NOT_EXIST(HttpStatus.NOT_FOUND, "Post content not found"),

    CATEGORY_NOT_EXIST(HttpStatus.NOT_FOUND, "Category not exist")

    ;



    private HttpStatus httpStatus;
    private String message;


    PostErrorCode(HttpStatus httpStatus, String message) {
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
