package com.ga.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ga.domain.util.DatabaseManager;
import com.ga.persistance.entity.User;

public class LoginDao {
	
	Connection con = null;
	ResultSet rs = null;
	
	public static String SQL_GET_LOGIN = "select * from UserTable where UserName = ? AND Password = ?";
	public static String SQL_GET_LOGIN_BY_ADMIN = "select * from UserTable where UserName = ?";
	public static String SQL_GET_PERMISSION = "select * from PermissionTable where Permission_Id IN (select Permission_Id from Role_PermissionTable where Role_Id = ?)";
	
	public ResultSet getLogin(User user) throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		
		PreparedStatement ps = con.prepareStatement(SQL_GET_LOGIN);
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPassword());
		
		rs = ps.executeQuery();
		
		return rs;
	}
	

	public ResultSet getLoginByAdmin(User user) throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		
		PreparedStatement ps = con.prepareStatement(SQL_GET_LOGIN_BY_ADMIN);
		ps.setString(1, user.getUserName());
		
		rs = ps.executeQuery();
		
		return rs;
	}
	
	public ResultSet getPermission(int role_id) throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		
		PreparedStatement ps = con.prepareStatement(SQL_GET_PERMISSION);
		ps.setInt(1, role_id);
		rs = ps.executeQuery();
		
		return rs;
	}
	
}
