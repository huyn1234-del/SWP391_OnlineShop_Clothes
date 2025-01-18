/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class OrderDetail {
      private int orderId;      
    private int productId;      
    private String productName; 
    private String thumbnail;   
    private int sizeId;         
    private int quantity;      
    private int unitPrice;      
    private int totalPrice;  
    private String sizeName;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int productId, String productName, String thumbnail, int sizeId, int quantity, int unitPrice, int totalPrice, String sizeName) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.thumbnail = thumbnail;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.sizeName = sizeName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    
    
    public OrderDetail(int orderId, int productId, String productName, String thumbnail, int sizeId, int quantity, int unitPrice, int totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.thumbnail = thumbnail;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
