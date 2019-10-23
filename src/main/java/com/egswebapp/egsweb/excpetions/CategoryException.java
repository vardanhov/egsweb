package com.egswebapp.egsweb.excpetions;

import com.egswebapp.egsweb.dto.response.ErrorResponse;
import com.egswebapp.egsweb.enums.ErrorCode;

import java.util.List;

public class CategoryException extends ServiceException {

    public CategoryException() {
    }

    public CategoryException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CategoryException(ErrorCode errorCode, List<ErrorResponse> errorResponses) {
        super(errorCode, errorResponses);
    }
}
