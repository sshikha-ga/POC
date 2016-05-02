package com.ga.mapper;

import java.util.List;

import com.ga.persistence.entity.Worklog;

public interface UserDao {
     void addWorkLog(Worklog log);
     List<Worklog> getWorkLogDetails(int task_id);
}
