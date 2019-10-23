package com.egswebapp.egsweb.dto.request;

import javax.validation.constraints.NotBlank;

public class PageDeleteRequestDto {

    @NotBlank(message = "languages can not be null")
    private  String languages;
    @NotBlank(message = "page id cannot be null")
    private String id;

    public PageDeleteRequestDto() {
    }

    public PageDeleteRequestDto(String languages, String id) {
        this.languages = languages;
        this.id = id;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
