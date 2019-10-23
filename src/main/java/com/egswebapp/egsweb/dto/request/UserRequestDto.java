package com.egswebapp.egsweb.dto.request;

import com.egswebapp.egsweb.dto.AbstractUserDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequestDto extends AbstractUserDto {


    @Email(message ="Email is not valid")
    @NotBlank(message = "email must no be empty")
    private String email;

    @NotBlank(message ="password must no be empty")
    @Size(min = 10, message = "password must be have a 10 symbol")
   // @Pattern(regexp = "\"[A-Za-z0-9 ]*", message = "in the password must be have a  uppercase latter")
    private String password;

    @NotBlank(message = "confirm password must not be empty and the confirm password must be same to password")
    private String confirmPassword;

    public UserRequestDto() {
    }

    public UserRequestDto(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

  public UserRequestDto(String name, String surname, String email, String password, String confirmPassword) {
        super(name, surname);
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
