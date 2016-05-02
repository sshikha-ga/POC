package com.ga.persistance.entity;

public class Role_Permission extends Base {

	int Role_Id;
	int Permission_Id;
	String Permission_Name;
	String Action;

	public int getRole_Id() {
		return Role_Id;
	}

	public void setRole_Id(int role_Id) {
		Role_Id = role_Id;
	}

	public int getPermission_Id() {
		return Permission_Id;
	}

	public void setPermission_Id(int permission_Id) {
		Permission_Id = permission_Id;
	}

	public String getPermission_Name() {
		return Permission_Name;
	}

	public void setPermission_Name(String permission_Name) {
		Permission_Name = permission_Name;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

}
