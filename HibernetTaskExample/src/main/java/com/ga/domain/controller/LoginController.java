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

import com.ga.persistence.entity.Permission;
import com.ga.persistence.entity.RolePermission;
import com.ga.persistence.entity.User;
import com.ga.repository.impl.LoginServiceImpl;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {          
        System.out.println("do post");
        doProcess(request, response);
    }
    
    private void doProcess(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equalsIgnoreCase("login")){
            try {
                checkLogin(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
    
    public void checkLogin(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
        
        RequestDispatcher rd = null;
        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        
        LoginServiceImpl loginService = new LoginServiceImpl();
        User user1 = loginService.getLogin(user);
        ArrayList<Permission> permissionList;
        
        try{
            permissionList = (ArrayList<Permission>) loginService.getPermission(user1.getRoleId().getRoleId());
            rd = request.getRequestDispatcher("/jsp/Home.jsp");
            request.setAttribute("Permissions", permissionList);
            HttpSession session = request.getSession();
            session.setAttribute("Permissions", permissionList);
            session.setAttribute("User", user1.getUserId());
            session.setAttribute("UserName", user1.getUserName());
            
        }catch(Exception e){
            System.out.println("null");
            rd = request.getRequestDispatcher("index.jsp");
        }
       
        rd.forward(request, response);
        
        /*if(rs.next()){
            ResultSet rs1 = new LoginDao().getPermission(rs.getInt("Role_Id")); 
            
            ArrayList<Role_Permission> permissions = new ArrayList<Role_Permission>();
            
            while(rs1.next()){
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
            
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/HomeJsp.jsp");
            rd.forward(request, response);
        }else{
            
        }*/
    }
}
