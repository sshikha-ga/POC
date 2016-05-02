package com.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.mapper.IUsermapper;
import com.ga.persistence.entity.User;
import com.ga.persistence.entity.UserGroup;
import com.ga.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUsermapper usermapper;

	@Override
	public List<UserGroup> getUser() {
		return usermapper.getUser();
	}

	@Override
	public List<UserGroup> findActiveUsers() {
		return usermapper.findActiveUsers();
	}

    @Override
    public List<User> findUserCriterias(List<String[]> arrayList,String clause) {
        return usermapper.findUserCriterias(arrayList,clause);
    }

    @Override
    public List<UserGroup> findUserGroupCriterias(List<String[]> arrayList, String clause) {
        return usermapper.findUserGroupCriterias(arrayList,clause);
    }	

}
