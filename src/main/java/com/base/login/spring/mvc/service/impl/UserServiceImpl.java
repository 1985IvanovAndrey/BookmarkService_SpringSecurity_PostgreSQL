package com.base.login.spring.mvc.service.impl;



import com.base.login.spring.mvc.dao.model.Role;
import com.base.login.spring.mvc.dao.model.UserInfo;
import com.base.login.spring.mvc.dao.repository.RoleRepository;
import com.base.login.spring.mvc.dao.repository.UserRepository;
import com.base.login.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<UserInfo>listUser=new ArrayList<>();
        for (UserInfo user : userRepository.findAll()) {
            UserInfo userInfo=new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setPassword((user.getPassword()));
            userInfo.setUsername(user.getUsername());
            for (Role role : user.getRoles()) {
                userInfo.setUserRole(role.getName());
            }
            //userInfo.setUserRole(user.getUserRole());
            listUser.add(userInfo);
        }
        return listUser;
    }

    @Override
    public void delUser(int id) {
        userRepository.deleteById(id);
    }
}