package com.ga.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.mapper.UserMapper;
import com.ga.persistence.entity.User;
import com.ga.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    public List<User> getAllUsers() {
        return mapper.getAllUsers();
    }

    public User getUserDetails(String userName) {
        return  mapper.getUserDetails(userName);
    }
    

}
