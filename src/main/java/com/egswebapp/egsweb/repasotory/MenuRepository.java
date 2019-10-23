package com.egswebapp.egsweb.repasotory;


import com.egswebapp.egsweb.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    int deleteMenuById(final Long id);
}
