package com.ga.repository;

import java.util.List;

import com.ga.persistence.entity.Permission;
import com.ga.persistence.entity.User;

public interface LoginService {
    public User getLogin(User user);
    public List<Permission> getPermission(int role_id);
}   
