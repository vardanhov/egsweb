package com.egswebapp.egsweb.model;

import com.egswebapp.egsweb.model.enums.Language;
import com.egswebapp.egsweb.model.enums.convert.LanguagesConverter;

import javax.persistence.*;

@Entity
@Table(name="menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "language_id")
    @Convert(converter = LanguagesConverter.class)
    protected Language language;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Menu() {
    }

    public Menu(String title, String url, Language language, Category category) {
        this.title = title;
        this.url = url;
        this.language = language;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
