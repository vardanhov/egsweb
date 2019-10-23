package com.egswebapp.egsweb.excpetions;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.ErrorCode;

import java.util.List;

public class AuthExceptions extends ServiceException {


    public AuthExceptions(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthExceptions(ErrorCode errorCode, List<ErrorResponse> errorResponses) {
        super(errorCode, errorResponses);
    }

    public AuthExceptions() {
    }
}
