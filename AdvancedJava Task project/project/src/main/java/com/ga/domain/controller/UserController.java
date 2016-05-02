package com.ga.domain.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ga.persistance.entity.Task;
import com.ga.persistance.entity.User;
import com.ga.persistance.entity.WorkLog;
import com.ga.repository.UserDao;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action").equalsIgnoreCase("register")) {

			User user = new User();

			user.setUserName(request.getParameter("userName"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRole_Id(Integer.parseInt(request.getParameter("role")));

			try {
				new UserDao().InsertUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/UserJsp.jsp");
			rd.forward(request, response);

		} else if (request.getParameter("action")
				.equalsIgnoreCase("CreateTask")) {
			try {
				createTask(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		
		}else if (request.getParameter("action").equalsIgnoreCase("DisplayTask")) {
			try {
				DisplayTask(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else if (request.getParameter("action").equalsIgnoreCase("addTask")) {
			try {
				addTask(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (request.getParameter("action").equalsIgnoreCase("getTaskDetails")) {
			try {
				getTaskDetails(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (request.getParameter("action").equalsIgnoreCase("WorkLog")) {
			try {
				WorkLog(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (request.getParameter("action").equalsIgnoreCase("addWorkLog")) {
			try {
				addWorkLog(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {

		ResultSet rs = new UserDao().getUsers();
		
		ArrayList<User> Users = new ArrayList<User>();
		
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("User_Id"));
			user.setUserName(rs.getString("UserName"));
			
			Users.add(user);
		}
		
		request.setAttribute("UserList",Users);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/AddTaskJsp.jsp");
		rd.forward(request, response);

	}

	public void addTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

		HttpSession session = request.getSession();
		
		Task task = new Task();
		task.setTitle(request.getParameter("title"));
		task.setDesc(request.getParameter("desc"));
		task.setStartDate(request.getParameter("startDate"));
		task.setEndDate(request.getParameter("endDate"));
		task.setCreatedBy((Integer) session.getAttribute("User"));
		task.setCreatedDate(new UserDao().getCurDateAndTime());
		
      /*  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");                       
        java.util.Date startDate=(Date) format.parse(request.getParameter("startDate"));   
        java.util.Date endDate=(Date) format.parse(request.getParameter("endDate")); */
        
        /*task.setStartDate(startDate);
        task.setEndDate(endDate);*/
        
		String[] assignedUser = request.getParameterValues("AssignedUsers");
		
		 List<String> assignedUserList = new ArrayList<String>(Arrays.asList(assignedUser));
		 assignedUserList.add(String.valueOf(task.getCreatedBy()));
		 
		task.setAssignedUsers(assignedUserList);
		
		new UserDao().addTask(task);
		
		request.setAttribute("TaskList", getAllTasks(task.getCreatedBy()));
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/TaskListJsp.jsp");
		rd.forward(request, response);

	}
	
	public ArrayList<Task> getAllTasks(int user_Id) throws SQLException, ClassNotFoundException{

		
		ArrayList<Task> taskList = new ArrayList<Task>();
		ResultSet rs1 = null;
		
		if(user_Id==1){
			rs1 = new UserDao().getAllTasks();
		}else{
			rs1 = new UserDao().getTasks(user_Id);
		}
		
		while (rs1.next()) {
			System.out.println("in rs");
			Task task = new Task();
			
			task.setId(rs1.getInt("Task_Id"));
			task.setTitle(rs1.getString("Title"));
			task.setStartDate(rs1.getString("StartDate"));
			task.setEndDate(rs1.getString("EndDate"));
			task.setDesc(rs1.getString("Description"));
			
			taskList.add(task);
		}
		
		System.out.println(taskList.size());
		
		return taskList;
	}

	public void DisplayTask(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		HttpSession session = request.getSession();
		ArrayList<WorkLog> worklogList = new ArrayList<WorkLog>();
		ArrayList<Task> taskList = getAllTasks((Integer)session.getAttribute("User"));
		ArrayList<Task> tasklist2 = new ArrayList<Task>();
		
		int minutes = 0;
		int hours = 0;
				
		for(int i=0;i<taskList.size();i++){
			Task task = taskList.get(i);
			
			ResultSet rs1 = new UserDao().getWorkLogDetails(task.getId());
			
			while(rs1.next()){
				
				hours += Integer.parseInt(rs1.getString("TotalHours"));
				minutes += Integer.parseInt(rs1.getString("TotalMinutes"));
			}
			
			task.setHours(hours);
			task.setMinutes(minutes);
			
			tasklist2.add(task);
			hours=0;
			minutes=0;
		}
		
		if((minutes % 60)>0){
			hours+=1;
			minutes = minutes % 60 ;
		}
		
		request.setAttribute("TaskList",tasklist2);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/TaskListJsp.jsp");
		rd.forward(request, response);
	}
	
	public void WorkLog(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		request.setAttribute("task_id", task_id);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/WorkLogJsp.jsp");
		rd.forward(request, response);
	}	
	
	public void addWorkLog(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{

		
		com.ga.persistance.entity.WorkLog log = new com.ga.persistance.entity.WorkLog();
		log.setTask_id(Integer.parseInt(request.getParameter("taskId")));
		log.setStartHour(request.getParameter("hour"));
		log.setStartMinute(request.getParameter("minute"));
		log .setTotalHour(request.getParameter("totalHour"));
		log.setTotalMinute(request.getParameter("totalMinute"));
		HttpSession session = request.getSession();
		int user_id = (Integer) session.getAttribute("User");
		log.setUser_id(user_id);
		log.setCreatedDate(new UserDao().getCurDateAndTime());
		
		new UserDao().addWorkLog(log);
		
		getTaskDetails(log.getTask_id(),request,response);
		
		//RequestDispatcher rd = request.getRequestDispatcher("/jsp/WorkLogJsp.jsp");
		//rd.forward(request, response);
	}
	
	public void getTaskDetails(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		int task_id = Integer.parseInt(request.getParameter("Task_Id"));
		System.out.println(task_id);
		ResultSet rs = new UserDao().getTasksDetails(task_id);
		Task task = new Task();
	
		if(rs.next()){
			task.setId(rs.getInt("Task_Id"));
			task.setTitle(rs.getString("Title"));
			task.setDesc(rs.getString("Description"));
			task.setStartDate(rs.getString("StartDate"));
			task.setEndDate(rs.getString("EndDate"));
		}
		
		request.setAttribute("Task", task);
		
		ResultSet rs1 = new UserDao().getWorkLogDetails(task_id);
		
		ArrayList<WorkLog> worklogList = new ArrayList<WorkLog>();
		while(rs1.next()){
			
			WorkLog log = new WorkLog();
			log.setTotalHour(rs1.getString("TotalHours"));
			log.setTotalMinute(rs1.getString("TotalMinutes"));
			log.setUser_id(rs1.getInt("User_Id"));
			log.setId(rs1.getInt("WorkLog_Id"));
			log.setUserName(new UserDao().getUserName(log.getUser_id()));
			worklogList.add(log);
		}
		
		request.setAttribute("WorkLogList", worklogList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/TaskDetailJsp.jsp");
		rd.forward(request, response);
	}
	
	public void getTaskDetails(int task_id,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		
		ResultSet rs = new UserDao().getTasksDetails(task_id);
		Task task = new Task();
	
		if(rs.next()){
			task.setId(rs.getInt("Task_Id"));
			task.setTitle(rs.getString("Title"));
			task.setDesc(rs.getString("Description"));
			task.setStartDate(rs.getString("StartDate"));
			task.setEndDate(rs.getString("EndDate"));
		}
		
		request.setAttribute("Task", task);
		
		ResultSet rs1 = new UserDao().getWorkLogDetails(task_id);
		
		ArrayList<WorkLog> worklogList = new ArrayList<WorkLog>();
		while(rs1.next()){
			
			WorkLog log = new WorkLog();
			log.setTotalHour(rs1.getString("TotalHours"));
			log.setTotalMinute(rs1.getString("TotalMinutes"));
			log.setUser_id(rs1.getInt("User_Id"));
			log.setId(rs1.getInt("WorkLog_Id"));
			log.setUserName(new UserDao().getUserName(log.getUser_id()));
			worklogList.add(log);
		}
		
		request.setAttribute("WorkLogList", worklogList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/TaskDetailJsp.jsp");
		rd.forward(request, response);
	}
	
}
