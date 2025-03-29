package model;

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
    private int totalAmount;
    private int totalGram;
    private int orderStatusId;
    private int saleId;

    private String orderStatusName;

    public Order() {
    }

    public Order(int orderId, int customerId, String orderedDate, String receiveDate, String receiverName, String phone, String email, String address, int totalPrice, int shippingFee, int voucherId, int totalAmount, int totalGram, int orderStatusId, int saleId) {
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
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.orderStatusId = orderStatusId;
        this.saleId = saleId;
    }

    
    
    public Order(int orderId, int customerId, String orderedDate, String receiveDate, String receiverName, String phone, String email, String address, int totalPrice, int shippingFee, int totalAmount, int totalGram, int orderStatusId, int saleId, String orderStatusName) {
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
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.orderStatusId = orderStatusId;
        this.saleId = saleId;
        this.orderStatusName = orderStatusName;
    }
    
    

    public Order(int orderId, int customerId, String orderedDate, String receiverName, String phone, String email, String address, int totalPrice, int shippingFee, int totalAmount, int totalGram, int orderStatusId, int saleId, String orderStatusName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.orderStatusId = orderStatusId;
        this.saleId = saleId;
        this.orderStatusName = orderStatusName;
    }
    
    

    public Order(int customerId, String orderedDate, String receiverName, String phone, String email, String address, int totalPrice, int shippingFee, int totalAmount, int totalGram, int orderStatusId) {
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.orderStatusId = orderStatusId;
    }
    
    

    public Order(int orderId, int customerId, String orderedDate, String receiverName, String phone, String email, String address, int totalPrice, int shippingFee, int totalAmount, int totalGram, int orderStatusId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.orderStatusId = orderStatusId;
    }
    
    public Order(int orderId, int customerId, String orderedDate, String receiverName, String phone, String email, String address, int totalPrice, int shippingFee, int totalAmount, int totalGram, int orderStatusId, int saleId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.orderStatusId = orderStatusId;
        this.saleId = saleId;
    }
    
    public Order(int customerId, String orderedDate, String receiverName, String phone, String email, String address, int totalPrice, int shippingFee, int totalAmount, int totalGram, int orderStatusId, int saleId) {
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.orderStatusId = orderStatusId;
        this.saleId = saleId;
    }
    public Order(int oid, int cid, String orderedDate, String receiveDate, String receiverName, String phone,
             String email, String address,
              int totalPrice, int shippingFee, int totalAmount, int totalGram,
             int orderStatusId , int saleId) {
     this.orderId = oid;
     this.customerId = cid;
     this.orderedDate = orderedDate;
     this.receiveDate = receiveDate;
     this.receiverName = receiverName;
     this.phone = phone;
     this.email = email;
     this.address = address;
     this.totalPrice = totalPrice;
     this.shippingFee = shippingFee;
     this.totalAmount = totalAmount;
     this.totalGram = totalGram;
     this.orderStatusId = orderStatusId;
     this.saleId = saleId;
}


    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }
    

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
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


    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalGram() {
        return totalGram;
    }

    public void setTotalGram(int totalGram) {
        this.totalGram = totalGram;
    }


    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
}

