package com.ga.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user_grop")
public class UserGroup {

	@EmbeddedId
	UserGroupPK usergroupPk;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id", referencedColumnName = "group_id", insertable = false, updatable = false)
	Group group;

	public UserGroupPK getUsergroupPk() {
		return usergroupPk;
	}

	public void setUsergroupPk(UserGroupPK usergroupPk) {
		this.usergroupPk = usergroupPk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}	
}
