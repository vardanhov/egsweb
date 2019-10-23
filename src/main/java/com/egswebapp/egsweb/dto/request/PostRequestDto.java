package com.egswebapp.egsweb.dto.request;

import javax.validation.constraints.NotBlank;

public class PostRequestDto {


    @NotBlank(message = "title may not be empty")
    private String title;

    @NotBlank(message = "description may not be empty")
    private String description;

    @NotBlank(message = "short text may not be empty")
    private String shortText;

    @NotBlank(message = "name of category may not be empty")
    private String categoryName;

    @NotBlank(message = "language may not be empty")
    private String language;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryId) {
        this.categoryName = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortText() {
        return shortText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;

    }
}
