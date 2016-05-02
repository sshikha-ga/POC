package com.ga.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int user_id;

	@Column(name = "FIRST_NAME")
	String firstName;

	@Column(name = "LAST_NAME")
	String lastName;

	@Column(name = "EMAIL")
	String email;

	@Column(name = "STATUS")
	String status;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	List<UserGroup> userGroupCollection;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserGroup> getUserGroupCollection() {
		return userGroupCollection;
	}

	public void setUserGroupCollection(List<UserGroup> userGroupCollection) {
		this.userGroupCollection = userGroupCollection;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", firstName=" + firstName + "]";
	}
}
