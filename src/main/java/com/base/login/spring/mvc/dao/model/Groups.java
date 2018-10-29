package com.base.login.spring.mvc.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nameGroup;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private List<Bookmark> bookmarks;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    public Groups(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Groups(String nameGroup, List<Bookmark> bookmarks) {
        this.nameGroup = nameGroup;
        this.bookmarks = bookmarks;
    }

    public Groups(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Groups() {
    }

    public Groups(String nameGroup, List<Bookmark> bookmarks, UserInfo userInfo) {
        this.nameGroup = nameGroup;
        this.bookmarks = bookmarks;
        this.userInfo = userInfo;
    }
}
