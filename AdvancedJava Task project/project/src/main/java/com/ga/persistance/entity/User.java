package com.ga.persistance.entity;

public class User extends Base {

	String UserName;
	String Password;
	String Email;
	int Role_Id;

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getRole_Id() {
		return Role_Id;
	}

	public void setRole_Id(int role_Id) {
		Role_Id = role_Id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

}
