package com.egswebapp.egsweb.model;

import com.egswebapp.egsweb.model.enums.Language;
import com.egswebapp.egsweb.model.enums.convert.LanguagesConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PageContentId implements Serializable {

    @Column(name = "post_id")
    private String pageId;

    @Column(name = "language_id")
    @Convert(converter = LanguagesConverter.class)
    private Language language;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageContentId that = (PageContentId) o;
        return Objects.equals(pageId, that.pageId) &&
                language == that.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageId, language);
    }
}
