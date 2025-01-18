/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class PostCategories {
    int post_category_id;
    String post_category_name;
    int is_active;

    public PostCategories() {
    }

    public PostCategories(int post_category_id, String post_category_name, int is_active) {
        this.post_category_id = post_category_id;
        this.post_category_name = post_category_name;
        this.is_active = is_active;
    }

    public int getPost_category_id() {
        return post_category_id;
    }

    public void setPost_category_id(int post_category_id) {
        this.post_category_id = post_category_id;
    }

    public String getPost_category_name() {
        return post_category_name;
    }

    public void setPost_category_name(String post_category_name) {
        this.post_category_name = post_category_name;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "PostCategories{" + "post_category_id=" + post_category_id + ", post_category_name=" + post_category_name + ", is_active=" + is_active + '}';
    }
    
}

