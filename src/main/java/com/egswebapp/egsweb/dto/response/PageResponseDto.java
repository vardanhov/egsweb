package com.egswebapp.egsweb.dto.response;

import com.egswebapp.egsweb.dto.AbstractPageDto;

public class PageResponseDto extends AbstractPageDto {

    private String id;
    private String userId;
    private String imgName;

    public PageResponseDto(String id, String userId, String imgName) {
        this.id = id;
        this.userId = userId;
        this.imgName = imgName;
    }

    public PageResponseDto(String title, String description, String languages, String id, String userId, String imgName) {
        super(title, description, languages);
        this.id = id;
        this.userId = userId;
        this.imgName = imgName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
