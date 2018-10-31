package com.base.login.spring.mvc.dao.repository;

import com.base.login.spring.mvc.dao.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * стандартный JPA репозиторий который работает с объектами.
 */
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByUsername(String username);
    UserInfo findById(int id);

}
