package com.egswebapp.egsweb.dto;




import javax.validation.constraints.NotBlank;


public abstract class AbstractUserDto {


    @NotBlank(message = "name must no be empty")
    private String name;

    @NotBlank(message = "surname must no be empty")
    private String surname;

    public AbstractUserDto() {
    }

    public AbstractUserDto(String name, String surname) {
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
