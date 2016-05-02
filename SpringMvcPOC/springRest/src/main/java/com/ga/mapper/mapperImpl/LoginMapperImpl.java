package com.ga.mapper.mapperImpl;

import org.springframework.stereotype.Repository;

import com.ga.domain.rowmappers.UserRowMapper;
import com.ga.domain.util.datasource;
import com.ga.mapper.LoginMapper;
import com.ga.persistence.entity.User;

@Repository
public class LoginMapperImpl extends datasource implements LoginMapper {

    private static String LOGIN_SQL = "select User_Id from user where UserName = ? and Password = ?";

    public Boolean checkLogin(User user) {

        try {

            getDataSource().queryForObject(LOGIN_SQL, new Object[] { user.getUserName(), user.getPassword() },
                    new UserRowMapper());

            return true;

        } catch (Exception e) {

            return false;

        }

    }

}
