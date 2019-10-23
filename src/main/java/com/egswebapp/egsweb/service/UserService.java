package com.egswebapp.egsweb.service;

import com.egswebapp.egsweb.dto.request.UserPasswordDto;
import com.egswebapp.egsweb.dto.request.UserRequestDto;
import com.egswebapp.egsweb.dto.request.UserUpdateDto;
import com.egswebapp.egsweb.dto.response.UserResponseDto;
import com.egswebapp.egsweb.model.User;
import com.egswebapp.egsweb.security.jwt.JwtUserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * UserService interface
 */


public interface UserService {


    /**
     * Map{@link UserRequestDto} to {@link com.egswebapp.egsweb.model.User}
     *
     * @param requestDto which to be save
     */
    void save(final UserRequestDto requestDto, final JwtUserDetails userDetails);


    /**
     * find user by email
     *
     * @param (email) return User
     **/
    User findUserByEmail(final String email);


    /**
     * write comment by swagger
     */
    void updateUserPassword(final UserPasswordDto dto, final JwtUserDetails userDetails);

    UserResponseDto findUserById(final String id);

    void updateUserData(final UserUpdateDto update, final String id);

    List<UserResponseDto> getAllUser();

    void addFile(final String id, final MultipartFile file);

    void delete(final String id);

    /**
     * change password
     */
    void changePassword(final String mail);


}
