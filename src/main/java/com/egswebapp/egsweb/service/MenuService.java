package com.egswebapp.egsweb.service;


import com.egswebapp.egsweb.dto.request.MenuRequestDto;
import com.egswebapp.egsweb.dto.response.MenuResponse;

import java.util.List;

public interface MenuService {



    /**
     *
     * Add Menu
     * @param menuRequestDto  the {@Link menuRequestDto}
     *
     */

    void addMenu(final MenuRequestDto menuRequestDto);

    /**
     *
     * update Menu
     * @param menuRequestDto  the {@Link menuRequestDto}
     * @param id  the {@Link id}
     *
     */

    MenuResponse updateMenu(final MenuRequestDto menuRequestDto, final Long id);



    /**
     *
     * Delete menu by  id
     *
     *@param id  the {@Link id} id of menu
     */

    void deleteMenu(final Long id);

    /**
     *
     * Get menu by  id
     *
     *@param id  the {@Link id} id of menu
     */
     MenuResponse getMenuById(final Long id);

    /**
     *
     * Get all Menu
     *
     */

    List<MenuResponse> getAllMenu();

}
