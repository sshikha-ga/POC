package com.ga.mapper;

import com.ga.persistence.entity.User;

public interface LoginMapper {

    Boolean checkLogin(User user);
    
}
