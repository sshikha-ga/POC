package com.ga.service;

import java.util.List;

import com.ga.persistence.entity.User;


public interface UserGroupService {

	/*
	 * Shop createShop(Shop shop); Shop deleteShop(int shopId); public
	 * List<Shop> findAll(); public Shop findById(int shopId); public Shop
	 * update(Shop shop);
	 */

	List<User> findUsers();
	
	List<User> findActiveUsers();
}
