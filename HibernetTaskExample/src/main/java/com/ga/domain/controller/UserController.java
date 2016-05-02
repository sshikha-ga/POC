package com.ga.domain.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ga.persistence.entity.Task;
import com.ga.persistence.entity.User;
import com.ga.persistence.entity.Worklog;
import com.ga.repository.impl.TaskServiceImpl;
import com.ga.repository.impl.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        System.out.println("do post");
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getParameter("action").equalsIgnoreCase("WorkLog")) {
            try {
                WorkLog(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (request.getParameter("action").equalsIgnoreCase("addWorkLog")) {
            try {
                addWorkLog(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void WorkLog(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException,
            SQLException, ServletException, IOException {

        int task_id = Integer.parseInt(request.getParameter("task_id"));
        request.setAttribute("task_id", task_id);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/WorkLog.jsp");
        rd.forward(request, response);
    }

    public void addWorkLog(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException,
            SQLException, ServletException, IOException {

        Worklog log = new Worklog();
        log.setTaskId(new Task(Integer.parseInt(request.getParameter("taskId"))));
        log.setStartTime(request.getParameter("hour") + request.getParameter("minute"));
        log.setTotalHours(request.getParameter("totalHour"));
        log.setTotalMinutes(request.getParameter("totalMinute"));
        HttpSession session = request.getSession();
        int user_id = (Integer) session.getAttribute("User");
        log.setUserId(new User(user_id));
        Date date = new Date();
        log.setCreatedDate(new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").format(date));

        UserServiceImpl userService = new UserServiceImpl();
        userService.addWorkLog(log);

        getTaskDetails(log.getTaskId().getTaskId(), request, response);

    }

    private void getTaskDetails(int task_id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TaskServiceImpl service = new TaskServiceImpl();
        Task task = service.getTaskDetails(task_id);
        request.setAttribute("Task", task);

        UserServiceImpl userService = new UserServiceImpl();
        ArrayList<Worklog> worklogList = (ArrayList<Worklog>) userService.getWorkLogDetails(task_id);

        request.setAttribute("WorkLogList", worklogList);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/TaskDetails.jsp");
        rd.forward(request, response);
    }
    
}
