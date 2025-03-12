/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Customer {
    public int user_id;
    public String first_name;
    public String last_name;
    public boolean gender;
    public String email;
    public String phone;
    public String dob;
    public boolean is_active;

    public Customer() {
    }

    public Customer(int user_id, String first_name, String last_name, boolean gender, String email, String phone, boolean is_active) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.is_active = is_active;
    }
    
    public Customer(int user_id, String first_name, String last_name, boolean gender, String email, String phone, String dob, boolean is_active) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.is_active = is_active;
    }

    public Customer( String first_name, String last_name, boolean gender, String email, String phone, String dob, boolean is_active,int user_id) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.is_active = is_active;
    }

    public Customer(String first_name, String last_name, String phone, String email, boolean gender,  String dob, boolean is_active) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.is_active = is_active;
    }
    
    
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "Customer{" + "user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", is_active=" + is_active + '}';
    }


    
    
}
