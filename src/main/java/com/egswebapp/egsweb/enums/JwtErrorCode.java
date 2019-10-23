package com.egswebapp.egsweb.enums;

import org.springframework.http.HttpStatus;

public enum  JwtErrorCode implements ErrorCode {
      TOKEN_EXPIRED(HttpStatus.BAD_REQUEST,"JWT token is expired or invalid");

    private HttpStatus status;
    private String message;

     JwtErrorCode(HttpStatus status, String message) {
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
