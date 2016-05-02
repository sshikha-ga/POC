package com.ga.service;

import java.util.List;

import com.ga.persistence.entity.User;

public interface UserService {

    List<User> getAllUsers();
    User getUserDetails(String userName);
}
