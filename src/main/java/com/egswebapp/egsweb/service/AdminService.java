package com.egswebapp.egsweb.service;

import com.egswebapp.egsweb.dto.request.UserUpdateRoleDto;

public interface AdminService {
    /**
     * update user data
     * Mpa{@link UserUpdateRoleDto} to {@link com.egswebapp.egsweb.model.User}
     *
     * @param updateDto
     */
    void roleUpdate(final UserUpdateRoleDto updateDto);

    /**
     * Activate User by Admin
     *
     * @param userId type of String
     */
    void activateUser(final String userId);


}

