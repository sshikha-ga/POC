package com.ga.mapper;

import java.util.List;

import com.ga.persistence.entity.Task;
import com.ga.persistence.entity.User;

/**
 * The Interface TaskDao.
 */
public interface TaskDao {
    
    /**
     * Gets the all tasks.
     *
     * @return the all tasks
     */
    List<Task> getAllTasks();
    
    /**
     * Adds the task.
     *
     * @param task the task
     */
    void addTask(Task task,List<User> assignedUserList);
    
    /**
     * Gets the task details.
     *
     * @param task_id the task_id
     * @return the task details
     */
    Task getTaskDetails(int task_id);
    
    public List<User> getUsers();
    
    public User getUserList(int user_id);
}
