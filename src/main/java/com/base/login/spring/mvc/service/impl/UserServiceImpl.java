package com.base.login.spring.mvc.service.impl;


import com.base.login.spring.mvc.dao.model.Bookmark;
import com.base.login.spring.mvc.dao.model.Groups;
import com.base.login.spring.mvc.dao.model.Role;
import com.base.login.spring.mvc.dao.model.UserInfo;
import com.base.login.spring.mvc.dao.repository.BookmarkRepository;
import com.base.login.spring.mvc.dao.repository.GroupsRepository;
import com.base.login.spring.mvc.dao.repository.RoleRepository;
import com.base.login.spring.mvc.dao.repository.UserRepository;
import com.base.login.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        Role role = roleRepository.getOne(2L);
        Set<Role> roles = new HashSet<>(Collections.singleton(role));
        userInfo.setRoles(roles);
        System.out.println(userInfo);
        System.out.println(roles);
        userRepository.save(userInfo);
    }

    @Override
    public UserInfo findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserInfo> userList() {
        List<UserInfo> listUser = new ArrayList<>();
        for (UserInfo user : userRepository.findAll()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setPassword((user.getPassword()));
            userInfo.setUsername(user.getUsername());
            for (Role role : user.getRoles()) {
                userInfo.setUserRole(role.getName());
            }
            listUser.add(userInfo);
        }
        return listUser;
    }

    @Override
    public void delUser(int id) {
        UserInfo userInfo = userRepository.findById(id);
        List<Groups> groupsList = groupsRepository.findAllByUserInfo(userInfo);
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (Groups groups : groupsList) {
            for (Bookmark bookmark : groups.getBookmarks()) {
                bookmarkList.add(bookmark);
            }
        }
        for (Bookmark bookmark : bookmarkList) {
            bookmarkRepository.deleteById(bookmark.getId());
        }
        userRepository.deleteById(id);
    }

    @Override
    public int getIdFromUser() {
        int id = 0;
        List<UserInfo> userList = userRepository.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (UserInfo userInfo : userList) {
            if (userInfo.getUsername().equals(authentication.getName())) {
                id = userInfo.getId();
            }
        }
        System.out.println(id);
        return id;
    }

    @Override
    public String getRoleByUser() {
        String roleUser=null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            roleUser=grantedAuthority.getAuthority();
        }
        System.out.println(roleUser+"++++++++");
        return roleUser;

    }
}

