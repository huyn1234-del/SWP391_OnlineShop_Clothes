/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Role {
    private int role_id;
    private String rolename;

    public Role() {
    }

    public Role(int role_id) {
        this.role_id = role_id;
    }

    public Role(int role_id, String rolename) {
        this.role_id = role_id;
        this.rolename = rolename;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role{" + "role_id=" + role_id + ", rolename=" + rolename + '}';
    }
}
