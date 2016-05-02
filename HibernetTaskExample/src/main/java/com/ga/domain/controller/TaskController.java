package com.ga.domain.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ga.domain.modal.TaskDto;
import com.ga.persistence.entity.Task;
import com.ga.persistence.entity.User;
import com.ga.persistence.entity.Worklog;
import com.ga.repository.TaskService;
import com.ga.repository.impl.TaskServiceImpl;
import com.ga.repository.impl.UserServiceImpl;

/**
 * Servlet implementation class TaskController.
 */
public class TaskController extends HttpServlet {

    /** The service. */
    TaskService service;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new task controller.
     */
    public TaskController() {
        super();
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProgress(request, response);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doProgress(request, response);
    }

    /**
     * Do progress.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void doProgress(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        /* display list of all the task and disply recent tasks on the top of list */
        if (request.getParameter("action").equalsIgnoreCase("displayTask")) {
            displayTasks(request, response);
        }
        /* Forwards to add Task Jsp*/
        else if(request.getParameter("action").equalsIgnoreCase("addTask")){
            
            TaskServiceImpl taskService = new TaskServiceImpl();
            List<User> userList = taskService.getUsers();
            request.setAttribute("UserList", userList);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/AddTask.jsp");
            rd.forward(request, response);
        }
        /* create task*/
        else if(request.getParameter("action").equalsIgnoreCase("saveTask")){
          addTask(request, response);
        }
        /*display details of particular task*/
        else if(request.getParameter("action").equalsIgnoreCase("getTaskDetails")){
            getTaskDetails(request, response);
        }
        /*Forwards to index*/
        else if(request.getParameter("action").equalsIgnoreCase("displayMenu")){
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        
    }

   
    /**
     * Display tasks.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void displayTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ArrayList<Worklog> worklogList = new ArrayList<Worklog>();
        TaskServiceImpl service = new TaskServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        List<Task> taskList = service.getAllTasks();
        ArrayList<TaskDto> tasklist2 = new ArrayList<TaskDto>();
        
        int minutes = 0;
        int hours = 0;
        
        for(int i=0;i<taskList.size();i++){
            Task task = taskList.get(i);
            TaskDto taskDto = new TaskDto();
            taskDto.setTaskId(task.getTaskId());
            taskDto.setTitle(task.getTitle());
            taskDto.setStartDate(task.getStartDate());
            taskDto.setEndDate(task.getEndDate());
            taskDto.setCreatedBy(task.getCreatedBy().getUserId());
            
            worklogList = (ArrayList<Worklog>) userService.getWorkLogDetails(task.getTaskId());
            
           for(int j=0;j<worklogList.size();j++){
                
                hours += Integer.parseInt(worklogList.get(j).getTotalHours());
                minutes += Integer.parseInt(worklogList.get(j).getTotalMinutes());
            }
            
            taskDto.setHours(hours);
            taskDto.setMinutes(minutes);
            
            tasklist2.add(taskDto);
            hours=0;
            minutes=0;
        }
        
        request.setAttribute("TaskList", tasklist2);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/Task.jsp");
        rd.forward(request, response);
    }

   
    /**
     * Adds the task.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void addTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskServiceImpl service = new TaskServiceImpl();
        Task task = new Task();
        task.setTitle(request.getParameter("title"));
        task.setDescription(request.getParameter("desc"));
        task.setStartDate(request.getParameter("startDate"));
        task.setEndDate(request.getParameter("endDate"));
        HttpSession session = request.getSession();
        Date date = new Date();
        task.setCreatedDate(new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").format(date));
        task.setCreatedBy(new User((Integer) session.getAttribute("User")));
        
        String[] assignedUser = request.getParameterValues("AssignedUsers");
        List<String> assignedUserList = new ArrayList<String>(Arrays.asList(assignedUser));
        assignedUserList.add(String.valueOf(task.getCreatedBy()));
        
        List<User> userList = new ArrayList<User>();
        
        for(int i=0;i<assignedUserList.size();i++){
            User user = service.getUserList(Integer.parseInt(assignedUserList.get(i)));
            userList.add(user);
        }
        
        service.addTask(task,userList);
        
        displayTasks(request, response);
    }
    
    
    /**
     * Gets the task details.
     *
     * @param request the request
     * @param response the response
     * @return the task details
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
   /* private void getTaskDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int task_id = Integer.parseInt(request.getParameter("Task_Id"));
        TaskServiceImpl service = new TaskServiceImpl();
        Task task = service.getTaskDetails(task_id);
        request.setAttribute("Task", task);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/TaskDetails.jsp");
        rd.forward(request, response);
    }*/
    
    private void getTaskDetails(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        int task_id = Integer.parseInt(request.getParameter("Task_Id"));
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
