package com.egswebapp.egsweb.repasotory;

import com.egswebapp.egsweb.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, String> {

}
