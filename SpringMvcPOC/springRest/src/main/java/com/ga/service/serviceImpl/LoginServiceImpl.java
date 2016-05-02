package com.ga.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.persistence.entity.User;
import com.ga.service.LoginService;
import com.ga.mapper.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper mapper;
    
    public Boolean checkLogin(User user) {
        return mapper.checkLogin(user);
    }

}
