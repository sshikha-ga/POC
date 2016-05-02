package com.ga.repository.impl;

import java.util.List;

import com.ga.mapper.impl.LoginDaoImpl;
import com.ga.mapper.impl.TaskDaoImpl;
import com.ga.persistence.entity.Permission;
import com.ga.persistence.entity.RolePermission;
import com.ga.persistence.entity.Task;
import com.ga.persistence.entity.User;
import com.ga.repository.LoginService;

public class LoginServiceImpl implements LoginService{
    
    @Override
    public User getLogin(User user) {
        LoginDaoImpl loginDao = new LoginDaoImpl();
        return loginDao.getLogin(user);
    }

    @Override
    public List<Permission> getPermission(int role_id){
        LoginDaoImpl loginDao = new LoginDaoImpl();
        return loginDao.getPermission(role_id);
    }
    
}
