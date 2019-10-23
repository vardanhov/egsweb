package com.egswebapp.egsweb.dto.request;

import javax.validation.constraints.NotBlank;

public class MenuRequestDto {

    @NotBlank(message = "title may not be empty")
    private String title;

    @NotBlank(message = "url may not be empty")
    private String url;

    @NotBlank(message = "language may not be empty")
    private String language;

    @NotBlank(message = "category may not be empty")
    private String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
