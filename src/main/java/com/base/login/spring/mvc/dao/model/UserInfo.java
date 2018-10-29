package com.base.login.spring.mvc.dao.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "email")
    private String username;

    @Column
    private String password;

    @Column
    private String userRole;

    @Column
    private String passwordConfirm;

    @Column
    private Set<Role> roles;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Groups> groupsSet = new ArrayList<>();
//
//    public List<Groups> getGroupsSet() {
//        return groupsSet;
//    }
//
//    public void setGroupsSet(List<Groups> groupsSet) {
//        this.groupsSet = groupsSet;
//    }

    public UserInfo(String username, String password, Set<Role> roles, Set<Groups> groupsSet) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserInfo() {
    }

}
