package com.ga.repository.impl;

import java.util.List;

import com.ga.mapper.UserDao;
import com.ga.mapper.impl.UserDaoImpl;
import com.ga.persistence.entity.Worklog;
import com.ga.repository.UserService;

public class UserServiceImpl implements UserService{

    @Override
    public void addWorkLog(Worklog log) {
         UserDaoImpl userdao = new UserDaoImpl();
         userdao.addWorkLog(log);
         
    }

    @Override
    public List<Worklog> getWorkLogDetails(int task_id) {
        UserDaoImpl userdao = new UserDaoImpl();
        return userdao.getWorkLogDetails(task_id);
    }   

}
