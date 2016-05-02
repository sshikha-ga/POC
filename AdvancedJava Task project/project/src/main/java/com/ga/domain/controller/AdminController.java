package com.ga.domain.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ga.persistance.entity.Role_Permission;
import com.ga.persistance.entity.User;
import com.ga.repository.LoginDao;

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//	
		if (request.getParameter("action").equalsIgnoreCase("RegisterUser")) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/UserRegistarion.jsp");
			rd.forward(request, response);

		} else if (request.getParameter("action").equalsIgnoreCase(
				"LoginByUser")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/LoginUser.jsp");
			rd.forward(request, response);

		} else if (request.getParameter("action").equalsIgnoreCase(
				"loginBy")) {
			try {
				checkLogin(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/*private  void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}*/

	public void checkLogin(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException {

		System.out.println("in login");
		
		User user = new User();
		user.setUserName(request.getParameter("username"));
		System.out.println(user.getUserName());
		ResultSet rs = new LoginDao().getLoginByAdmin(user);

		if (rs.next()) {
			ResultSet rs1 = new LoginDao().getPermission(rs.getInt("Role_Id"));

			ArrayList<Role_Permission> permissions = new ArrayList<Role_Permission>();

			while (rs1.next()) {
				Role_Permission rp = new Role_Permission();
				rp.setPermission_Id(rs1.getInt("Permission_Id"));
				rp.setPermission_Name(rs1.getString("PermissionName"));
				rp.setAction(rs1.getString("Action"));
				permissions.add(rp);
			}

			HttpSession session = request.getSession();
			session.setAttribute("Permissions", permissions);
			session.setAttribute("User", rs.getInt("User_Id"));
			session.setAttribute("UserName", rs.getString("UserName"));

			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/HomeJsp.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}
