package com.ga.domain.modal;

public class RolePermissionDto {

    int role_Id;
    String roleName;
    int permission_Id;
    String permissionName;
    String action;

    public int getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(int role_Id) {
        this.role_Id = role_Id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getPermission_Id() {
        return permission_Id;
    }

    public void setPermission_Id(int permission_Id) {
        this.permission_Id = permission_Id;
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

}
