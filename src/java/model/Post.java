/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Post {
    int post_id;
    String title, content,thumbnail;
    int author_id, is_active;
    Date created_at, modified_at;
    int post_category_id;
    String author_name;

    public Post() {
    }

    public Post(int post_id, String title, String content, String thumbnail, int author_id, int is_active, Date created_at, Date modified_at, int post_category_id) {
        this.post_id = post_id;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.author_id = author_id;
        this.is_active = is_active;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.post_category_id = post_category_id;
    }

    public Post(int post_id, String title, String content, String thumbnail, int author_id, int is_active, Date created_at, Date modified_at, int post_category_id, String author_name) {
        this.post_id = post_id;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.author_id = author_id;
        this.is_active = is_active;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.post_category_id = post_category_id;
        this.author_name = author_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
    
    

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public int getPost_category_id() {
        return post_category_id;
    }

    public void setPost_category_id(int post_category_id) {
        this.post_category_id = post_category_id;
    }
    
    

           
    
}
