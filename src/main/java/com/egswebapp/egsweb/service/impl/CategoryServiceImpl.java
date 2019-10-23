package com.egswebapp.egsweb.service.impl;

import com.egswebapp.egsweb.dto.request.CategoryRequestDto;
import com.egswebapp.egsweb.enums.CategoryErrorCode;
import com.egswebapp.egsweb.excpetions.CategoryException;
import com.egswebapp.egsweb.model.Category;
import com.egswebapp.egsweb.repasotory.CategoryRepository;
import com.egswebapp.egsweb.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    private MapperFacade mapperFacade;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, MapperFacade mapperFacade) {
        this.repository = repository;
        this.mapperFacade = mapperFacade;
    }

    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public void save(final CategoryRequestDto categoryDto) {
        log.info("trying to save category");
        if (repository.existsCategoriesByName(categoryDto.getName())) {
            log.error("category name already exist");
            throw new CategoryException(CategoryErrorCode.CATEGORY_IS_EXIST);
        }
        final Category category = mapperFacade.map(categoryDto, Category.class);
        repository.save(category);
        log.info("category is create");
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Long id) {
        log.info("trying to delete category");
        repository.deleteById(id);
        log.info("category is deleted");
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void rename(Long id, CategoryRequestDto category) {
        log.info("tyring to rename category name");
        final Category cat = repository.findById(id).orElseThrow(() -> new CategoryException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        if (cat.getName().equalsIgnoreCase(category.getName())) {
            log.error("category name already exist");
            throw new CategoryException(CategoryErrorCode.CATEGORY_IS_EXIST);
        }
        cat.setName(category.getName());
        repository.save(cat);
        log.info("name is changed");

    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategory() {
        return repository.findAll();
    }


}
