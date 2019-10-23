package com.egswebapp.egsweb.service.impl;

import com.egswebapp.egsweb.dto.request.MenuRequestDto;
import com.egswebapp.egsweb.dto.response.MenuResponse;
import com.egswebapp.egsweb.enums.CategoryErrorCode;
import com.egswebapp.egsweb.enums.MenuErrorCode;
import com.egswebapp.egsweb.excpetions.CategoryException;
import com.egswebapp.egsweb.excpetions.MenuException;
import com.egswebapp.egsweb.model.Category;
import com.egswebapp.egsweb.model.Menu;
import com.egswebapp.egsweb.model.enums.Language;
import com.egswebapp.egsweb.repasotory.CategoryRepository;
import com.egswebapp.egsweb.repasotory.MenuRepository;
import com.egswebapp.egsweb.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final CategoryRepository categoryRepository;


    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, CategoryRepository categoryRepository) {
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;

    }


    @Override
    @Transactional(readOnly = true)
    public List<MenuResponse> getAllMenu() {
        List<Menu> listMenu = menuRepository.findAll();
        if (CollectionUtils.isEmpty(listMenu)) {
            return Collections.EMPTY_LIST;
        }
        List<MenuResponse> result = listMenu.stream().map(temp -> {
            MenuResponse obj = new MenuResponse();
            obj.setTitle(temp.getTitle());
            obj.setUrl(temp.getUrl());
            obj.setLanguage(temp.getLanguage().getName());
            obj.setCategory(temp.getCategory().getName());
            return obj;
        }).collect(Collectors.toList());
        log.info("Menu added success");
        return result;
    }


    @Override
    @Transactional
    public void addMenu(MenuRequestDto menuRequestDto) {
        Menu menu = new Menu();
        menu.setTitle(menuRequestDto.getTitle());
        menu.setUrl(menuRequestDto.getUrl());
        final Category category = categoryRepository.getCategoryByName(menuRequestDto.getCategory());
        if (category == null) {
            log.info("Category with this id not exist");
            throw new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }
        menu.setLanguage(Language.forName(menuRequestDto.getLanguage()));
        menu.setCategory(category);
        menuRepository.save(menu);
        log.info("Menu added success");
    }

    @Override
    @Transactional
    public MenuResponse updateMenu(MenuRequestDto menuRequestDto, Long id) {
        Menu menu = menuRepository.getOne(id);
        menu.setTitle(menuRequestDto.getTitle());
        menu.setUrl(menuRequestDto.getUrl());
        menu.setLanguage(Language.forName(menuRequestDto.getLanguage()));
        Menu updatedMenu = menuRepository.saveAndFlush(menu);
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setTitle(updatedMenu.getTitle());
        menuResponse.setUrl(updatedMenu.getUrl());
        menuResponse.setCategory(updatedMenu.getCategory().getName());
        menuResponse.setLanguage(updatedMenu.getLanguage().getName());
        log.info("Menu updated success");
        return menuResponse;
    }

    @Override
    @Transactional
    public MenuResponse getMenuById(final Long id) {

        final Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuException(MenuErrorCode.MENU_NOT_EXIST));
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setTitle(menu.getTitle());
        menuResponse.setUrl(menu.getUrl());
        menuResponse.setCategory(menu.getCategory().getName());
        menuResponse.setLanguage(menu.getLanguage().getName());
        log.info("Menu found  success");
        return menuResponse;
    }

    @Override
    @Transactional
    public void deleteMenu(final Long id) {
        int count = menuRepository.deleteMenuById(id);
        if (count != 1) {
            throw new MenuException(MenuErrorCode.MENU_NOT_EXIST);
        }
        log.info("Menu deleted success");
    }
}
