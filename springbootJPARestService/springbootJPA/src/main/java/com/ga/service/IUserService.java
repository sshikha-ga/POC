package com.ga.service;

import java.util.List;

import com.ga.persistence.entity.User;
import com.ga.persistence.entity.UserGroup;

public interface IUserService {

    public List<UserGroup> getUser();

    public List<UserGroup> findActiveUsers();

    public List<User> findUserCriterias(List<String[]> arrayList, String clause);

    public List<UserGroup> findUserGroupCriterias(List<String[]> arrayList, String clause);

}
