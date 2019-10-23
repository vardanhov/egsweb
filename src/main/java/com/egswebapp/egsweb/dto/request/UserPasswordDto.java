package com.egswebapp.egsweb.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserPasswordDto {



    @NotBlank(message = "password must no be empty")
    private String currentPassword;

    @NotBlank(message = "password must no be empty")
    @Size(min = 10, message = "password must be have a 10 symbol")
    // @Pattern(regexp = "\"[A-Za-z0-9 ]*", message = "in the password must be have a  uppercase latter")
    private String password;

    @NotBlank(message = "password must no be empty")
    private String confirmPassword;

    public UserPasswordDto() {
    }

    public UserPasswordDto(String currentPassword, String password, String confirmPassword) {
    ;
        this.currentPassword = currentPassword;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
