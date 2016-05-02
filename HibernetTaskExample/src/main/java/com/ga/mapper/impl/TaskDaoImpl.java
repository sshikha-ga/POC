package com.ga.mapper.impl;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ga.domain.util.HibernateUtil;
import com.ga.mapper.TaskDao;
import com.ga.persistence.entity.Assigntask;
import com.ga.persistence.entity.Task;
import com.ga.persistence.entity.User;

/**
 * The Class TaskDaoImpl.
 */
public class TaskDaoImpl implements TaskDao {

    private static String SQL_INSERT_AssignedUsers = "Insert into AssignTable(Task_Id,User_Id) values(?,?)";

    
    /*return list of tasks*/
    /* (non-Javadoc)
     * @see com.ga.mapper.TaskDao#getAllTasks()
     */
    public List<Task> getAllTasks() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("Task.findAll");
        List<Task> listTask = query.list();

        return listTask;
    }

    /*add tasks*/
    /* (non-Javadoc)
     * @see com.ga.mapper.TaskDao#addTask(com.ga.persistence.entity.Task)
     */
    @Override
    public void addTask(Task task,List<User> assignedUserList) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(task);
        
        for(int i=0;i<assignedUserList.size();i++){
            Assigntask assigntask = new Assigntask();
            User user = assignedUserList.get(i);
            assigntask.setAssignUserId(user);
            assigntask.setAssignTaskId(new Task(task.getTaskId()));
            session.save(assigntask);
        }
        
        session.getTransaction().commit();
        session.close();
    }

    
    public User getUserList(int user_id){
        
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query query = session.getNamedQuery("User.findByUserId");
        query.setParameter("userId",user_id);
        User user = (User) query.list().get(0);
        return user;
    }
    /*display task details*/
    /* (non-Javadoc)
     * @see com.ga.mapper.TaskDao#getTaskDetails(int)
     */
    @Override
    public Task getTaskDetails(int task_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("Task.findByTaskId");
        query.setParameter("taskId", task_id);
        List<Task> tasklist = query.list();
        
        Iterator<Task> iterator = tasklist.iterator();
        Task task = new Task();
        
        if(iterator.hasNext()){
            task = iterator.next();
        }
        
        return task;
    }
    
    public List<User> getUsers(){

           Session session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           Query query = session.getNamedQuery("User.findAll");
           
           List<User> userList = query.list();
           return userList;
    }

}
