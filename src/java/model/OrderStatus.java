/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class OrderStatus {
    private int orderStatusId;
    private String orderStatusName;
    private String description;
    private boolean isActive;

    public OrderStatus() {
    }

    public OrderStatus(int orderStatusId, String orderStatusName, String description, boolean isActive) {
        this.orderStatusId = orderStatusId;
        this.orderStatusName = orderStatusName;
        this.description = description;
        this.isActive = isActive;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "OrderStatus{" + "orderStatusId=" + orderStatusId + ", orderStatusName=" + orderStatusName + ", description=" + description + ", isActive=" + isActive + '}';
    }
    
    
}
