package com.base.login.spring.mvc.service;


import com.base.login.spring.mvc.dao.model.UserInfo;

import java.util.List;

public interface UserService {

    void save(UserInfo userInfo);

    UserInfo findByUsername(String username);

    List<UserInfo> userList();

    void delUser(int id);

    int getIdFromUser();

    String getRoleByUser();
}
