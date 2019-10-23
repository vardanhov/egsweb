package com.egswebapp.egsweb.enums;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getMessage();

    HttpStatus getStatus();
}
