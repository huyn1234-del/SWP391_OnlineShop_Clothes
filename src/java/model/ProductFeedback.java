/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class ProductFeedback {
    int feedback_id, customer_id, order_id,product_id;
    String review;
    String thumbnail;
    int rating, is_active;
    Date create_at, update_at;
    String customer_name, customer_img;
    
    
    Product product;
    User user;
    

    public ProductFeedback() {
    }

    public ProductFeedback(int feedback_id, String review, String thumbnail, int rating) {
        this.feedback_id = feedback_id;
        this.review = review;
        this.thumbnail = thumbnail;
        this.rating = rating;
    }
    
    

    public ProductFeedback(int customer_id, int order_id, int product_id, String review, String thumbnail, int rating) {
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.review = review;
        this.thumbnail = thumbnail;
        this.rating = rating;
    }

    
    
    public ProductFeedback(int feedback_id, int customer_id, int order_id, String review, String thumbnail, int rating, Date create_at, Date update_at, Product product) {
        this.feedback_id = feedback_id;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.review = review;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.create_at = create_at;
        this.update_at = update_at;
        this.product = product;
    }

    
    public ProductFeedback(int feedback_id, int customer_id, int order_id, String review, String thumbnail, int rating, int is_active, Date create_at, Date update_at, Product product, User user) {
        this.feedback_id = feedback_id;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.review = review;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.is_active = is_active;
        this.create_at = create_at;
        this.update_at = update_at;
        this.product = product;
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    
    public ProductFeedback(int feedback_id, int customer_id, int order_id, int product_id, String review, String thumbnail, int rating, int is_active, Date create_at, Date update_at, String customer_name, String customer_img) {
        this.feedback_id = feedback_id;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.review = review;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.is_active = is_active;
        this.create_at = create_at;
        this.update_at = update_at;
        this.customer_name = customer_name;
        this.customer_img = customer_img;
    }

    
    
    public ProductFeedback(int feedback_id, int customer_id, int order_id, int product_id, String review, int rating, int is_active) {
        this.feedback_id = feedback_id;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.review = review;
        this.rating = rating;
        this.is_active = is_active;
    }

    public ProductFeedback(int feedback_id, int customer_id, int order_id, int product_id, String review, String thumbnail, int rating, int is_active) {
        this.feedback_id = feedback_id;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.review = review;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.is_active = is_active;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_img() {
        return customer_img;
    }

    public void setCustomer_img(String customer_img) {
        this.customer_img = customer_img;
    }
    
    

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
    
    
    
}
