package com.ga.repository.impl;

import java.util.List;

import com.ga.mapper.impl.TaskDaoImpl;
import com.ga.persistence.entity.Task;
import com.ga.persistence.entity.User;
import com.ga.repository.TaskService;

/**
 * The Class TaskServiceImpl.
 */
public class TaskServiceImpl implements TaskService {

    /*return list of tasks*/
    /* (non-Javadoc)
     * @see com.ga.repository.TaskService#getAllTasks()
     */
    public List<Task> getAllTasks() {
        TaskDaoImpl taskDao = new TaskDaoImpl();
        return taskDao.getAllTasks();
    }

    /*add tasks*/
    /* (non-Javadoc)
     * @see com.ga.repository.TaskService#addTask(com.ga.persistence.entity.Task)
     */
    @Override
    public void addTask(Task task,List<User> assignedUserList) {
        TaskDaoImpl taskDao = new TaskDaoImpl();
        taskDao.addTask(task,assignedUserList);
    }

    /*display task details*/
    /* (non-Javadoc)
     * @see com.ga.repository.TaskService#getTaskDetails(int)
     */
    @Override
    public Task getTaskDetails(int task_id) {
        TaskDaoImpl taskDao = new TaskDaoImpl();
        return taskDao.getTaskDetails(task_id);
    }

    @Override
    public List<User> getUsers() {
        TaskDaoImpl taskDao = new TaskDaoImpl();
        return taskDao.getUsers();
    }

    @Override
    public User getUserList(int user_id) {
        TaskDaoImpl taskDao = new TaskDaoImpl();
        return taskDao.getUserList(user_id);
    }

}
