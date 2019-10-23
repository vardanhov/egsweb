package com.egswebapp.egsweb.excpetions;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.ErrorCode;

import java.util.List;

public class MenuException extends ServiceException

{
    public MenuException() {
    }

    public MenuException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MenuException(ErrorCode errorCode, List<ErrorResponse> errorResponses) {
        super(errorCode, errorResponses);
    }
}
