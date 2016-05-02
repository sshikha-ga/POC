package com.ga.service;

import com.ga.persistence.entity.User;

public interface LoginService {

    Boolean checkLogin(User user);
    
}
