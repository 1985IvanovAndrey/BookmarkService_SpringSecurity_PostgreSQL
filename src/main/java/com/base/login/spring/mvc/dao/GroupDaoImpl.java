package com.base.login.spring.mvc.dao;

import com.base.login.spring.mvc.dao.model.Bookmark;
import com.base.login.spring.mvc.dao.model.Groups;
import com.base.login.spring.mvc.dao.model.UserInfo;
import com.base.login.spring.mvc.dao.repository.BookmarkRepository;
import com.base.login.spring.mvc.dao.repository.GroupsRepository;
import com.base.login.spring.mvc.dao.repository.UserRepository;
import com.base.login.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional
@Repository
public class GroupDaoImpl implements GroupDao {

    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Override
    public List<Groups> listGroup() {
        return groupsRepository.findAll();
    }

    @Override
    public void addGroup(Groups group) {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = userService.findByUsername(aut.getName());
        group.setUserInfo(userInfo);
        groupsRepository.saveAndFlush(group);
    }

    @Override
    public void deleteGroup(int id) {
        groupsRepository.deleteById(id);
    }

    @Override
    public void deleteBookmark(int id) {
        bookmarkRepository.deleteById(id);
    }

    @Override
    public Groups getById(int id) {
        return groupsRepository.findById(id);
    }

    @Override
    public List<Bookmark> listBookmark() {
        return bookmarkRepository.findAll();
    }

    @Override
    public void addBookmark(Bookmark bookmark, int id) {
        List<Groups> groups = listGroup();
        List<Bookmark> bookmarkList = new ArrayList<>();
        //groups = listGroup();
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == id) {
                System.out.println("Good" + i);
                for (int j = 0; j < groups.get(i).getBookmarks().size(); j++) {
                    bookmarkList.add(groups.get(i).getBookmarks().get(j));
                }
            }
        }
        bookmarkList.add(checkUrlBookmark(bookmark));
        Groups group = getById(id);
        group.setBookmarks(bookmarkList);
        addGroup(group);
    }

    @Override
    public List<Bookmark> getBookmarksfromOneGroup(int id) {
        List<Bookmark> bookmarkList = new ArrayList<>();
        List<Groups> groups = new ArrayList<>();
        groups = listGroup();
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == id) {
                for (int j = 0; j < groups.get(i).getBookmarks().size(); j++) {
                    bookmarkList.add(groups.get(i).getBookmarks().get(j));
                }
            }
        }
        System.out.println(bookmarkList);
        return bookmarkList;
    }

    @Override
    public boolean checkNewGroupOnDublicate(Groups group) {
        boolean chech = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Groups> groupsList = groupsRepository.findAllByUserInfo_Username(authentication.getName());
        for (Groups groups : groupsList) {
            if (groups.getNameGroup().equals(group.getNameGroup())) {
                chech = true;
            }
        }
        return chech;
    }

    public Bookmark checkUrlBookmark(Bookmark bookmark) {
        Pattern p = Pattern.compile("^(http?)");
        Matcher m = p.matcher(bookmark.getUrlBookmark());
        if (m.find()) {
            return bookmark;
        } else {
            bookmark.setUrlBookmark("http://" + bookmark.getUrlBookmark());
            return bookmark;
        }
    }

    @Override
    public List<Groups> getGroupsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Groups> groupsList = groupsRepository.findAllByUserInfo_Username(authentication.getName());
        return groupsList;
    }
}

