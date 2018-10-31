package com.base.login.spring.mvc.dao.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private List<Bookmark> bookmarks;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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
