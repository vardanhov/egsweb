package com.egswebapp.egsweb.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public abstract class AbstractCategoryDto {

    @NotBlank(message = "category name can not be null")
    private  String name;

    public AbstractCategoryDto() {
    }

    public AbstractCategoryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
