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
@Table(name = "tbl_group")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int GROUP_ID;

	@Column(name = "GROUP_NAME")
	String groupName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	List<UserGroup> userGroupCollection;

	public int getGROUP_ID() {
		return GROUP_ID;
	}

	public void setGROUP_ID(int gROUP_ID) {
		GROUP_ID = gROUP_ID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<UserGroup> getUserGroupCollection() {
		return userGroupCollection;
	}

	public void setUserGroupCollection(List<UserGroup> userGroupCollection) {
		this.userGroupCollection = userGroupCollection;
	}

	@Override
	public String toString() {
		return "Group [GROUP_ID=" + GROUP_ID + ", groupName=" + groupName
				+ ", userGroupCollection=" + userGroupCollection + "]";
	}

}
