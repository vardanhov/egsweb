package com.egswebapp.egsweb.dto;

import javax.validation.constraints.NotBlank;

public abstract class AbstractPageDto {

    @NotBlank(message = "title must not be empty")
    private String title;

    @NotBlank(message = "description must not be empty")
    private String description;

    @NotBlank(message = "languages must not be empty")
    private String languages;

    public AbstractPageDto() {
    }

    public AbstractPageDto(String title, String description, String languages) {
        this.title = title;
        this.description = description;
        this.languages = languages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
