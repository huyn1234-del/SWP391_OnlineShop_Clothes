/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class User {
     private int user_id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private boolean gender;
    private String dob;
    private String reset_password_code;
    private String profile_picture_url;
    private boolean is_active;
    private boolean is_banned;
    private Role role;

    public User() {
    }
    public User(int user_id, String first_name, String last_name, String phone, boolean gender, String dob) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
    }
    
    public User(int user_id, String username, String password, String first_name, String last_name, String phone, String email, boolean gender, String dob, String reset_password_code, String profile_picture_url, boolean is_active, boolean is_banned, Role role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.reset_password_code = reset_password_code;
        this.profile_picture_url = profile_picture_url;
        this.is_active = is_active;
        this.is_banned = is_banned;
        this.role = role;
    }

    public User(String username, String password, String firstname, String lastname, String phone, String userEmail, boolean gender, String dob, Object object, Object object0, String profile_imgdefaultjpg, boolean b, boolean b0, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User(String username, String password, String firstname, String lastname, String phone, String userEmail, boolean gender, String dob, String verificationCode, Object object, Object object0, String profile_imgdefaultjpg, boolean b, boolean b0, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getReset_password_code() {
        return reset_password_code;
    }

    public void setReset_password_code(String reset_password_code) {
        this.reset_password_code = reset_password_code;
    }

    public String getProfile_picture_url() {
        return profile_picture_url;
    }

    public void setProfile_picture_url(String profile_picture_url) {
        this.profile_picture_url = profile_picture_url;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_banned() {
        return is_banned;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
}
