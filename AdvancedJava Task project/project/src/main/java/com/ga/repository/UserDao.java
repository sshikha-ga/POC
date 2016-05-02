package com.ga.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.ga.domain.util.DatabaseManager;
import com.ga.persistance.entity.Task;
import com.ga.persistance.entity.User;
import com.ga.persistance.entity.WorkLog;

public class UserDao {

	Connection con = null;
	ResultSet rs = null;

	private static String SQL_INSERT_USER = "Insert into UserTable(UserName,Password,Email,Role_Id) values(?,?,?,?)";
	private static String SQL_FIND_ALL_USERS = "Select * from UserTable";
	private static String SQL_INSERT_Task = "Insert into TaskTable(Title,Description,StartDate,EndDate,CreatedDate,CreatedBy) values(?,?,?,?,?,?)";
	private static String SQL_INSERT_AssignedUsers = "Insert into AssignTable(Task_Id,User_Id) values(?,?)";
	private static String SQL_GET_Tasks_BY_USER_ID = "Select * from TaskTable where Task_Id IN ( select Task_Id from AssignTable where User_Id = ?) Order by CreatedDate DESC";
	private static String SQL_GET_Tasks_DETAILS = "Select * from TaskTable where Task_Id = ?";
	private static String SQL_GET_ALL_Tasks = "Select * from TaskTable Order by CreatedDate";
	private static String SQL_INSERT_WORKLOG = "Insert into WorkLogTable(StartTime,TotalHours,TotalMinutes,Task_Id,User_Id,CreatedDate) values(?,?,?,?,?,?)";
	private static String SQL_GET_CURRENT_DATETIME = "select now() as now";
	private static String SQL_GET_WorkLOG_BY_TaskID = "Select * from WorkLogTable where Task_Id = ?";
	private static String SQL_GET_UserName_BY_UserID = "Select UserName from UserTable where User_Id = ?";
	
	public void InsertUser(User user) throws SQLException, ClassNotFoundException {
		con = new DatabaseManager().getConnection();

		PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER);
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		ps.setInt(4, user.getRole_Id());

		ps.executeUpdate();
		
		con.close();

	}
	
	public String getCurDateAndTime() throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		Statement stmt =  con.createStatement();
		rs = stmt.executeQuery(SQL_GET_CURRENT_DATETIME);
		
		String date=null;
		if(rs.next()){
			date = rs.getString("now");
		}
		return date;
	}
	
	public String getUserName(int user_id) throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		PreparedStatement ps = con.prepareStatement(SQL_GET_UserName_BY_UserID);
		ps.setInt(1, user_id);
		rs = ps.executeQuery();
		
		String uname=null;
		if(rs.next()){
			uname = rs.getString("UserName");
		}
		return uname;
	}
	
	public void addWorkLog(WorkLog log) throws SQLException, ClassNotFoundException {
		con = new DatabaseManager().getConnection();

		PreparedStatement ps = con.prepareStatement(SQL_INSERT_WORKLOG);
		ps.setString(1, log.getStartHour()+" h "+log.getStartMinute()+" m ");
		ps.setString(2, log.getTotalHour());
		ps.setString(3, log.getTotalMinute());
		ps.setInt(4, log.getTask_id());
		ps.setInt(5, log.getUser_id());
		ps.setString(6, log.getCreatedDate());

		ps.executeUpdate();
		
		con.close();

	}
	
	public ResultSet getUsers() throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		Statement stmt =  con.createStatement();
		rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
		return rs;
		
	}
	
	public ResultSet getTasks(int user_id) throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		PreparedStatement ps = con.prepareStatement(SQL_GET_Tasks_BY_USER_ID);
		ps.setInt(1, user_id);
		rs = ps.executeQuery();
		return rs;
		
	}
	
	public ResultSet getAllTasks() throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(SQL_GET_ALL_Tasks);
		return rs;
		
	}
	

	public ResultSet getTasksDetails(int task_id) throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		PreparedStatement ps = con.prepareStatement(SQL_GET_Tasks_DETAILS);
		ps.setInt(1, task_id);
		rs = ps.executeQuery();
		return rs;
		
	}
	
	public ResultSet getWorkLogDetails(int task_id) throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();
		PreparedStatement ps = con.prepareStatement(SQL_GET_WorkLOG_BY_TaskID);
		ps.setInt(1, task_id);
		rs = ps.executeQuery();
		return rs;
		
	}
	
	
	public void addTask(Task task) throws ClassNotFoundException, SQLException{

		con = new DatabaseManager().getConnection();

		PreparedStatement ps = con.prepareStatement(SQL_INSERT_Task, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, task.getTitle());
		ps.setString(2, task.getDesc());
		ps.setString(3, task.getStartDate());
		ps.setString(4, task.getEndDate());
		ps.setString(5, task.getCreatedDate());
		ps.setInt(6, task.getCreatedBy());

		ps.executeUpdate();
	
		ResultSet tableKeys = ps.getGeneratedKeys();
		tableKeys.next();
		int Task_id = tableKeys.getInt(1);
		
		con.close();

		con = new DatabaseManager().getConnection();
		
		List<String> AssignedUsers  = task.getAssignedUsers();
		
		for(int i=0;i<AssignedUsers.size();i++){
			PreparedStatement ps1 = con.prepareStatement(SQL_INSERT_AssignedUsers);
			ps1.setInt(1, Task_id);
			ps1.setInt(2, Integer.parseInt(AssignedUsers.get(i)));
			ps1.executeUpdate();
		}
	}
	
}
