package com.ga.mapper;

import java.util.List;

import com.ga.persistence.entity.Permission;
import com.ga.persistence.entity.User;

public interface LoginDao {
    public User getLogin(User user);
    public List<Permission> getPermission(int role_id);
}
