package com.egswebapp.egsweb.repasotory;


import com.egswebapp.egsweb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsCategoriesByName(final String name);

    void deleteById(final Long id);
    Category getCategoryByName(final String name);


}
