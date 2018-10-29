package com.base.login.spring.mvc.dao.repository;


import com.base.login.spring.mvc.dao.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
}
