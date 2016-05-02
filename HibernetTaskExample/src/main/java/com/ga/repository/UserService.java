package com.ga.repository;

import java.util.List;

import com.ga.persistence.entity.Worklog;

public interface UserService {
     void addWorkLog(Worklog log);
     List<Worklog> getWorkLogDetails(int task_id);
}
