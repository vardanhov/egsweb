package com.egswebapp.egsweb.service;

import com.egswebapp.egsweb.dto.request.UserLoginDto;
import com.egswebapp.egsweb.dto.response.UserResponseDto;


public interface AuthService {

    /**
     * @param loginDto
     */

    UserResponseDto login(final UserLoginDto loginDto);
}
