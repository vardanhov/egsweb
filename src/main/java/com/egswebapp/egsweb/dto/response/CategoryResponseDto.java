package com.egswebapp.egsweb.dto.response;

import com.egswebapp.egsweb.dto.AbstractCategoryDto;

public class CategoryResponseDto extends AbstractCategoryDto {

    private Long id;

    public CategoryResponseDto(Long id) {
        this.id = id;
    }

    public CategoryResponseDto(String name, Long id) {
        super(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
