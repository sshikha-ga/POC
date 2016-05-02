package com.ga.mapper;

import java.util.List;

import com.ga.persistence.entity.User;

public interface UserMapper {

    List<User> getAllUsers();
    User getUserDetails(String userName);
}
