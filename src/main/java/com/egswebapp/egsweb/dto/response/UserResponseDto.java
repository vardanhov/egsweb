package com.egswebapp.egsweb.dto.response;

import com.egswebapp.egsweb.dto.AbstractUserDto;
import com.egswebapp.egsweb.model.enums.UserProfile;
import com.egswebapp.egsweb.model.enums.convert.UserProfileConverter;

import javax.persistence.Convert;

public class UserResponseDto extends AbstractUserDto {


    private String id;

    private String imgName;

    @Convert(converter = UserProfileConverter.class)
    private UserProfile userProfile;

    public UserResponseDto() {
    }

    public UserResponseDto(String id, String imgName, UserProfile userProfile) {
        this.id = id;
        this.imgName = imgName;
        this.userProfile = userProfile;
    }

    public UserResponseDto(String name, String surname, String id, String imgName, UserProfile userProfile) {
        super(name, surname);
        this.id = id;
        this.imgName = imgName;
        this.userProfile = userProfile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
