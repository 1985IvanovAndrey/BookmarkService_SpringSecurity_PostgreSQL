package com.base.login.spring.mvc.dao.repository;

import com.base.login.spring.mvc.dao.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Groups,Integer> {
    Groups findById(int id);
}
