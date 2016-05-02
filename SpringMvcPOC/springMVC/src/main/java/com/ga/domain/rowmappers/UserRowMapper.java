package com.ga.domain.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ga.persistence.entity.User;

public class UserRowMapper implements RowMapper<User>{

    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        
        User user = new User();
        user.setUserId(rs.getInt("User_Id"));
        return user;
    }

}
