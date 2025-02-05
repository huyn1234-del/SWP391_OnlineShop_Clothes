/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Order {
    private int orderId;
    private int customerId;
    private String orderedDate;
    private String receiveDate;
    private String receiverName;
    private String phone;
    private String email;
    private String address;
    private int totalPrice;
    private int shippingFee;
    private int voucherId;
    private int voucherPercent;
    private int totalAmount;
    private int paymentMethodId;
    private int paymentStatusId;
    private int orderStatusId;
    private String shippingCode;
    private int saleId;

    public Order() {
    }

    public Order(int orderId, int customerId, String orderedDate, String receiveDate, String receiverName, String phone, String email, String address, int totalPrice, int shippingFee, int voucherId, int voucherPercent, int totalAmount, int paymentMethodId, int paymentStatusId, int orderStatusId, String shippingCode, int saleId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiveDate = receiveDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.voucherPercent = voucherPercent;
        this.totalAmount = totalAmount;
        this.paymentMethodId = paymentMethodId;
        this.paymentStatusId = paymentStatusId;
        this.orderStatusId = orderStatusId;
        this.shippingCode = shippingCode;
        this.saleId = saleId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(int shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getVoucherPercent() {
        return voucherPercent;
    }

    public void setVoucherPercent(int voucherPercent) {
        this.voucherPercent = voucherPercent;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", customerId=" + customerId + ", orderedDate=" + orderedDate + ", receiveDate=" + receiveDate + ", receiverName=" + receiverName + ", phone=" + phone + ", email=" + email + ", address=" + address + ", totalPrice=" + totalPrice + ", shippingFee=" + shippingFee + ", voucherId=" + voucherId + ", voucherPercent=" + voucherPercent + ", totalAmount=" + totalAmount + ", paymentMethodId=" + paymentMethodId + ", paymentStatusId=" + paymentStatusId + ", orderStatusId=" + orderStatusId + ", shippingCode=" + shippingCode + ", saleId=" + saleId + '}';
    }

    
    
}
