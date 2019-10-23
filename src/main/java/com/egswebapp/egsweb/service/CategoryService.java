package com.egswebapp.egsweb.service;

import com.egswebapp.egsweb.dto.request.CategoryRequestDto;
import com.egswebapp.egsweb.dto.request.UserRequestDto;
import com.egswebapp.egsweb.model.Category;

import java.util.List;

public interface CategoryService {


    /**
     * Map{@link CategoryRequestDto} to {@link com.egswebapp.egsweb.model.Category}
     *
     * @param category which to be save
     */

    void save(final CategoryRequestDto category);



    /**
     * delete category by id
     * @param id
     * */
    void delete(final Long id);


    /**
     * rename category by id
     *
     * @param id
     * @param  category
     * */
    void rename(final Long id, final CategoryRequestDto category);

    List<Category> getAllCategory();




}
