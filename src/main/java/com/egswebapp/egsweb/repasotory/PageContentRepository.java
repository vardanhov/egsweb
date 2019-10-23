package com.egswebapp.egsweb.repasotory;

import com.egswebapp.egsweb.model.PageContent;
import com.egswebapp.egsweb.model.PageContentId;
import com.egswebapp.egsweb.model.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageContentRepository extends JpaRepository<PageContent, PageContentId> {
}

