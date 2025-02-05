/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author user
 */
public class FeedBack {
    private int feedbackId;
    private int customerId;
    private int orderId;
    private int productId;
    private String review;
    private String thumbnail;
    private int rating;
    private boolean isActive;
    private Date crateAt;
    private Date modifiedAt;

    public FeedBack() {
    }

    public FeedBack(int feedbackId, int customerId, int orderId, int productId, String review, String thumbnail, int rating, boolean isActive, Date crateAt, Date modifiedAt) {
        this.feedbackId = feedbackId;
        this.customerId = customerId;
        this.orderId = orderId;
        this.productId = productId;
        this.review = review;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.isActive = isActive;
        this.crateAt = crateAt;
        this.modifiedAt = modifiedAt;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCrateAt() {
        return crateAt;
    }

    public void setCrateAt(Date crateAt) {
        this.crateAt = crateAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "FeedBack{" + "feedbackId=" + feedbackId + ", customerId=" + customerId + ", orderId=" + orderId + ", productId=" + productId + ", review=" + review + ", thumbnail=" + thumbnail + ", rating=" + rating + ", isActive=" + isActive + ", crateAt=" + crateAt + ", modifiedAt=" + modifiedAt + '}';
    }
    
    
}
