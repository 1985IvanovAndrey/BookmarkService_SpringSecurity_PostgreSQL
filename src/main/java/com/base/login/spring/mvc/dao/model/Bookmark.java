package com.base.login.spring.mvc.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@Entity
@Table
public class Bookmark {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column
    private String bookmark;

    @Column(name = "url_bookmark")
    private String urlBookmark;

    @Column
    private String description;

    public Bookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public Bookmark(String bookmark, String urlBookmark, String description) {
        this.bookmark = bookmark;
        this.urlBookmark = urlBookmark;
        this.description = description;
    }

    public Bookmark() {
    }
}
