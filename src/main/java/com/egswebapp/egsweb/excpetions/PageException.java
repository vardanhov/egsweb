package com.egswebapp.egsweb.excpetions;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.ErrorCode;

import java.util.List;

public class PageException extends ServiceException {

    public PageException() {
    }

    public PageException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PageException(ErrorCode errorCode, List<ErrorResponse> errorResponses) {
        super(errorCode, errorResponses);
    }
}
