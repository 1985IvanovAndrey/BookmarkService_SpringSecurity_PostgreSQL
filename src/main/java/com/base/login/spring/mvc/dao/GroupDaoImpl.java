package com.base.login.spring.mvc.dao;

import com.base.login.spring.mvc.dao.model.Bookmark;
import com.base.login.spring.mvc.dao.model.Groups;
import com.base.login.spring.mvc.dao.model.UserInfo;
import com.base.login.spring.mvc.dao.repository.BookmarkRepository;
import com.base.login.spring.mvc.dao.repository.GroupsRepository;
import com.base.login.spring.mvc.dao.repository.UserRepository;
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

    //    @Autowired
//    private SessionFactory sessionFactory;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Groups> listGroup() {
//        Session session = sessionFactory.getCurrentSession();
//        List<Groups> listGroup = session.createQuery("from Groups").list();
//        return listGroup;
        return groupsRepository.findAll();
    }

    @Override
    public void addGroup(Groups group) {
//        Session session = sessionFactory.getCurrentSession();
//        session.saveOrUpdate(group);
        UserInfo userInfo = new UserInfo();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (UserInfo user : userRepository.findAll()) {
            if (user.getUsername().equals(authentication.getName())) {
                userInfo.setId(user.getId());
            }
        }
        group.setUserInfo(userInfo);
        groupsRepository.save(group);

    }

    @Override
    public void deleteGroup(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Groups group = (Groups) session.load(Groups.class, new Integer(id));
//        if (group != null) {
//            session.delete(group);
//        }
        groupsRepository.deleteById(id);
    }
    @Override
    public void deleteBookmark(int id) {
        bookmarkRepository.deleteById(id);
    }
    @Override
    public Groups getById(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Groups group = (Groups) session.get(Groups.class, new Integer(id));
        //System.out.println(group);
//        return group;
        return groupsRepository.findById(id);
    }

    @Override
    public List<Bookmark> listBookmark() {
//        Session session = sessionFactory.getCurrentSession();
//        List<Bookmark> listBookmark = session.createQuery("from Bookmark").list();
//        return listBookmark;
        return bookmarkRepository.findAll();
    }

    @Override
    public void addBookmark(Bookmark bookmark, int id) {
        List<Groups> groups = new ArrayList<>();
        List<Bookmark> bookmarkList = new ArrayList<>();
        groups = listGroup();
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
        int userId = 0;
        List<Groups> groupsList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (UserInfo user : userRepository.findAll()) {
            if (user.getUsername().equals(authentication.getName())) {
                userId = user.getId();
            }
        }

        boolean chech = false;
        //List<Groups> groupsList = listGroup();
        for (Groups groups : groupsRepository.findAll()) {
            if (userId == groups.getUserInfo().getId()) {
                groupsList.add(groups);
            }
        }
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
        int userId = 0;
        List<Groups> groupsList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (UserInfo user : userRepository.findAll()) {
            if (user.getUsername().equals(authentication.getName())) {
                userId = user.getId();
            }
        }
        for (Groups groups : groupsRepository.findAll()) {
            if (userId == groups.getUserInfo().getId()) {
                groupsList.add(groups);
            }
        }
        return groupsList;
    }
}

