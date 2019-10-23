package com.egswebapp.egsweb.dto.request;

import com.egswebapp.egsweb.dto.AbstractPageDto;

public class PageRequestDto extends AbstractPageDto {

    public PageRequestDto() {
    }

    public PageRequestDto(String title, String description, String languages) {
        super(title, description, languages);
    }
}
