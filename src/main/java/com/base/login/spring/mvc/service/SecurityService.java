package com.base.login.spring.mvc.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
