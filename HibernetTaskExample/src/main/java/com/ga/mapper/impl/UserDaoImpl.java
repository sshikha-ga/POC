package com.ga.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ga.domain.util.HibernateUtil;
import com.ga.mapper.UserDao;
import com.ga.persistence.entity.Worklog;

public class UserDaoImpl implements UserDao{

    @Override
    public void addWorkLog(Worklog log) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(log);
        session.getTransaction().commit();
        session.close();
    }
    
    public List<Worklog> getWorkLogDetails(int task_id){
       
        Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("Worklog.findByWorkLogTaskId");
        query.setParameter("taskId", task_id);
        ArrayList<Worklog> worklogList = (ArrayList<Worklog>) query.list();
        return worklogList;
    }
    
}
