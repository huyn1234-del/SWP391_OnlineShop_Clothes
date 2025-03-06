package model;


public class PaymentStatus {
    int paymentStatusId;
    String paymentStatusName;
    String description;

    public PaymentStatus() {
    }

    public PaymentStatus(int paymentStatusId, String paymentStatusName, String description) {
        this.paymentStatusId = paymentStatusId;
        this.paymentStatusName = paymentStatusName;
        this.description = description;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public String getPaymentStatusName() {
        return paymentStatusName;
    }

    public void setPaymentStatusName(String paymentStatusName) {
        this.paymentStatusName = paymentStatusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
