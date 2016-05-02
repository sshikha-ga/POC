package com.ga.mapper.mapperImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ga.domain.rowmappers.UserRowMapper;
import com.ga.domain.util.datasource;
import com.ga.mapper.UserMapper;
import com.ga.persistence.entity.User;

@Repository
public class UserMapperImpl extends datasource implements UserMapper {

    private static String GET_USER_BY_ID = "select * from user where UserName = ?";
    private static String GET_USERS = "select * from user";

    public List<User> getAllUsers() {
        return getDataSource().query(GET_USERS,new UserRowMapper());
    }
    
    public User getUserDetails(String userName) {
        return getDataSource().queryForObject(GET_USER_BY_ID, new Object[]{userName} , new UserRowMapper());
    }

}
