package com.base.login.spring.mvc.dao.repository;


import com.base.login.spring.mvc.dao.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark,Integer>{
}
