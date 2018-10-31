package com.base.login.spring.mvc.appConfig;//package service.bookmark.config;


import com.base.login.spring.mvc.dao.model.Role;
import com.base.login.spring.mvc.dao.model.UserInfo;
import com.base.login.spring.mvc.dao.repository.BookmarkRepository;
import com.base.login.spring.mvc.dao.repository.GroupsRepository;
import com.base.login.spring.mvc.dao.repository.RoleRepository;
import com.base.login.spring.mvc.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.PostConstruct;
import java.awt.List;
import java.util.*;

@Transactional
@Component
public class InitDataToDb {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    @PostConstruct
    private void init() {

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("admin");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("user");
        roleRepository.save(role2);


        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("boss@gmail.com");
        userInfo.setPassword(bCryptPasswordEncoder.encode("12345678"));
        Set<Role> roles = new HashSet<>(Collections.singleton(role1));
        userInfo.setRoles(roles);
        userRepository.save(userInfo);

// //        Groups group=new Groups();
// //        Bookmark bookmark=new Bookmark();
// //        bookmark.setBookmark("main");
// //        bookmark.setDescription("111");
// //        bookmark.setUrlBookmark("i.ua");
// //        bookmarkRepository.save(bookmark);
// //        ArrayList<Bookmark>bookmarkList=new ArrayList<>();
// //        bookmarkList.add(bookmark);
// //        group.setNameGroup("first");
// //        group.setBookmarks(bookmarkList);
// //       // groupsRepository.save(group);
// //        UserInfo userInfo1=new UserInfo();
// //        userInfo1.setUsername("test");
// //        Set<Role> roles1 = new HashSet<>(Collections.singleton(role2));
// //        userInfo1.setRoles(roles1);
// //        ArrayList<Groups>groupsArrayList=new ArrayList<>();
// //        groupsArrayList.add(group);
// //        //userInfo1.getGroupsSet(groupsArrayList);
// //        userRepository.save(userInfo1);

    }
 }
