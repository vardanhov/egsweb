package com.egswebapp.egsweb.dto.request;

import javax.validation.constraints.NotBlank;

public class UserUpdateDto {


    @NotBlank(message = "name is empty")
    private String name;

    @NotBlank(message = "surname is empty")
    private String surname;


    public UserUpdateDto() {
    }

    public UserUpdateDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
