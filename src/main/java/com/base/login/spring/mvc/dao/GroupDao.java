package com.base.login.spring.mvc.dao;


import com.base.login.spring.mvc.dao.model.Bookmark;
import com.base.login.spring.mvc.dao.model.Groups;

import java.util.List;

public interface GroupDao {

    public List<Groups> listGroup();

    void addGroup(Groups group);

    void deleteGroup(int id);

    Groups getById(int id);

    public List<Bookmark> listBookmark();

    void addBookmark(Bookmark bookmark, int id);

    List<Bookmark> getBookmarksfromOneGroup(int id);

    boolean checkNewGroupOnDublicate(Groups group);

    List<Groups> getGroupsForUser();

    void deleteBookmark(int id);
}
