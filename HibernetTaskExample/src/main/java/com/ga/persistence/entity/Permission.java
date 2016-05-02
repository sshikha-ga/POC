/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.persistence.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author NIRAJ
 */
@Entity
@Table(name = "permission")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p"),
        @NamedQuery(name = "Permission.findByPermissionId", query = "SELECT p FROM Permission p WHERE p.permissionId = :permissionId"),
        @NamedQuery(name = "Permission.findByPermissionName", query = "SELECT p FROM Permission p WHERE p.permissionName = :permissionName"),
        @NamedQuery(name = "Permission.findByAction", query = "SELECT p FROM Permission p WHERE p.action = :action") })
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Permission_Id")
    private Integer permissionId;
    @Column(name = "PermissionName")
    private String permissionName;
    @Column(name = "Action")
    private String action;
    @OneToMany(mappedBy = "permissionId")
    private Collection<RolePermission> rolePermissionCollection;

    public Permission() {
    }

    public Permission(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @XmlTransient
    public Collection<RolePermission> getRolePermissionCollection() {
        return rolePermissionCollection;
    }

    public void setRolePermissionCollection(Collection<RolePermission> rolePermissionCollection) {
        this.rolePermissionCollection = rolePermissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionId != null ? permissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) object;
        if ((this.permissionId == null && other.permissionId != null)
                || (this.permissionId != null && !this.permissionId.equals(other.permissionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Permission [permissionId=" + permissionId + ", permissionName=" + permissionName + ", action=" + action
                + ", rolePermissionCollection=" + rolePermissionCollection + "]";
    }

}
