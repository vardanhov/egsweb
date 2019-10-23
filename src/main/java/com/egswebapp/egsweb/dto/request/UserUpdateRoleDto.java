package com.egswebapp.egsweb.dto.request;

import javax.validation.constraints.NotBlank;

public class UserUpdateRoleDto {

    @NotBlank(message = "user userId must be not empty")
    private String userId;

    @NotBlank(message = "user role must be not empty")
    private String role;


    public UserUpdateRoleDto() {
    }

    public UserUpdateRoleDto(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
