package com.base.login.spring.mvc.dao.repository;

import com.base.login.spring.mvc.dao.model.Groups;
import com.base.login.spring.mvc.dao.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Integer> {

    Groups findById(int id);

    List<Groups> findAllByUserInfo_Username(String name);

    List<Groups>findAllByUserInfo(UserInfo userInfo);

    List<Groups>findAllByUserInfo_Id(int id);
}
