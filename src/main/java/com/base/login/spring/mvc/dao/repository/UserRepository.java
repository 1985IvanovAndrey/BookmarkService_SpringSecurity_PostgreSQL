package com.base.login.spring.mvc.dao.repository;

import com.base.login.spring.mvc.dao.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * стандартный JPA репозиторий который работает с объектами.
 */
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUsername(String username);
    void deleteById(int id);
}
