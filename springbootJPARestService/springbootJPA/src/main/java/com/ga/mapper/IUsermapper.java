package com.ga.mapper;

import java.util.List;

import com.ga.persistence.entity.User;
import com.ga.persistence.entity.UserGroup;

public interface IUsermapper {

    List<UserGroup> getUser();

    List<UserGroup> findActiveUsers();

    public List<User> findUserCriterias(List<String[]> arrayList, String clause);

    public List<UserGroup> findUserGroupCriterias(List<String[]> arrayList, String clause);
}
