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
    private String wardCode;
    private String wardName;
    private int districtId;
    private String districtName;
    private int provinceId;
    private String provinceName;
    private int totalPrice;
    private int shippingFee;
    private int voucherId;
    private int voucherPercent;
    private int totalAmount;
    private int totalGram;
    private int paymentMethodId;
    private String vnpTxnRef;
    private String vnpCreateDate;
    private int paymentStatusId;
    private int orderStatusId;
    private String shippingCode;
    private int saleId;
    
    private String paymentMethodName;
    private String paymentStatusName;
    private String orderStatusName;

    public Order() {
    }

    public Order(int orderId, int customerId, String orderedDate, String receiveDate, String receiverName, String phone, String email, String address, String wardCode, String wardName, int districtId, String districtName, int provinceId, String provinceName, int totalPrice, int shippingFee, int voucherId, int voucherPercent, int totalAmount, int totalGram, int paymentMethodId, String vnpTxnRef, String vnpCreateDate, int paymentStatusId, int orderStatusId, String shippingCode, int saleId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiveDate = receiveDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.wardCode = wardCode;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.voucherPercent = voucherPercent;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.paymentMethodId = paymentMethodId;
        this.vnpTxnRef = vnpTxnRef;
        this.vnpCreateDate = vnpCreateDate;
        this.paymentStatusId = paymentStatusId;
        this.orderStatusId = orderStatusId;
        this.shippingCode = shippingCode;
        this.saleId = saleId;
    }

    
    
    public Order(int orderId, int customerId, String orderedDate, String receiveDate, String receiverName, String phone, String email, String address, String wardCode, String wardName, int districtId, String districtName, int provinceId, String provinceName, int totalPrice, int shippingFee, int voucherId, int voucherPercent, int totalAmount, int totalGram, int paymentMethodId, String vnpTxnRef, String vnpCreateDate, int paymentStatusId, int orderStatusId, String shippingCode, int saleId, String paymentMethodName, String paymentStatusName, String orderStatusName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiveDate = receiveDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.wardCode = wardCode;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.voucherPercent = voucherPercent;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.paymentMethodId = paymentMethodId;
        this.vnpTxnRef = vnpTxnRef;
        this.vnpCreateDate = vnpCreateDate;
        this.paymentStatusId = paymentStatusId;
        this.orderStatusId = orderStatusId;
        this.shippingCode = shippingCode;
        this.saleId = saleId;
        this.paymentMethodName = paymentMethodName;
        this.paymentStatusName = paymentStatusName;
        this.orderStatusName = orderStatusName;
    }
    
    

    public Order(int orderId, int customerId, String orderedDate, String receiverName, String phone, String email, String address, String wardCode, String wardName, int districtId, String districtName, int provinceId, String provinceName, int totalPrice, int shippingFee, int voucherId, int voucherPercent, int totalAmount, int totalGram, int paymentMethodId, String vnpTxnRef, String vnpCreateDate, int paymentStatusId, int orderStatusId, String shippingCode, int saleId, String paymentMethodName, String paymentStatusName, String orderStatusName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.wardCode = wardCode;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.voucherPercent = voucherPercent;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.paymentMethodId = paymentMethodId;
        this.vnpTxnRef = vnpTxnRef;
        this.vnpCreateDate = vnpCreateDate;
        this.paymentStatusId = paymentStatusId;
        this.orderStatusId = orderStatusId;
        this.shippingCode = shippingCode;
        this.saleId = saleId;
        this.paymentMethodName = paymentMethodName;
        this.paymentStatusName = paymentStatusName;
        this.orderStatusName = orderStatusName;
    }
    
    

    public Order(int customerId, String orderedDate, String receiverName, String phone, String email, String address, String wardCode, String wardName, int districtId, String districtName, int provinceId, String provinceName, int totalPrice, int shippingFee, int voucherId, int voucherPercent, int totalAmount, int totalGram, int paymentMethodId, int paymentStatusId, int orderStatusId) {
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.wardCode = wardCode;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.voucherPercent = voucherPercent;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.paymentMethodId = paymentMethodId;
        this.paymentStatusId = paymentStatusId;
        this.orderStatusId = orderStatusId;
    }
    
    

    public Order(int orderId, int customerId, String orderedDate, String receiverName, String phone, String email, String address, String wardCode, String wardName, int districtId, String districtName, int provinceId, String provinceName, int totalPrice, int shippingFee, int voucherId, int voucherPercent, int totalAmount, int totalGram, int paymentMethodId, String vnpTxnRef, String vnpCreateDate, int paymentStatusId, int orderStatusId, String shippingCode) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.wardCode = wardCode;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.voucherPercent = voucherPercent;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.paymentMethodId = paymentMethodId;
        this.vnpTxnRef = vnpTxnRef;
        this.vnpCreateDate = vnpCreateDate;
        this.paymentStatusId = paymentStatusId;
        this.orderStatusId = orderStatusId;
        this.shippingCode = shippingCode;
    }
    
    public Order(int orderId, int customerId, String orderedDate, String receiverName, String phone, String email, String address, String wardCode, String wardName, int districtId, String districtName, int provinceId, String provinceName, int totalPrice, int shippingFee, int voucherId, int voucherPercent, int totalAmount, int totalGram, int paymentMethodId, String vnpTxnRef, String vnpCreateDate, int paymentStatusId, int orderStatusId, String shippingCode, int saleId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.wardCode = wardCode;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.voucherPercent = voucherPercent;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.paymentMethodId = paymentMethodId;
        this.vnpTxnRef = vnpTxnRef;
        this.vnpCreateDate = vnpCreateDate;
        this.paymentStatusId = paymentStatusId;
        this.orderStatusId = orderStatusId;
        this.shippingCode = shippingCode;
        this.saleId = saleId;
    }
    
    public Order(int customerId, String orderedDate, String receiverName, String phone, String email, String address, String wardCode, String wardName, int districtId, String districtName, int provinceId, String provinceName, int totalPrice, int shippingFee, int voucherId, int voucherPercent, int totalAmount, int totalGram, int paymentMethodId, String vnpTxnRef, String vnpCreateDate, int paymentStatusId, int orderStatusId, String shippingCode, int saleId) {
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.receiverName = receiverName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.wardCode = wardCode;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.voucherPercent = voucherPercent;
        this.totalAmount = totalAmount;
        this.totalGram = totalGram;
        this.paymentMethodId = paymentMethodId;
        this.vnpTxnRef = vnpTxnRef;
        this.vnpCreateDate = vnpCreateDate;
        this.paymentStatusId = paymentStatusId;
        this.orderStatusId = orderStatusId;
        this.shippingCode = shippingCode;
        this.saleId = saleId;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }
    
    

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getPaymentStatusName() {
        return paymentStatusName;
    }

    public void setPaymentStatusName(String paymentStatusName) {
        this.paymentStatusName = paymentStatusName;
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

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public int getTotalGram() {
        return totalGram;
    }

    public void setTotalGram(int totalGram) {
        this.totalGram = totalGram;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getVnpTxnRef() {
        return vnpTxnRef;
    }

    public void setVnpTxnRef(String vnpTxnRef) {
        this.vnpTxnRef = vnpTxnRef;
    }

    public String getVnpCreateDate() {
        return vnpCreateDate;
    }

    public void setVnpCreateDate(String vnpCreateDate) {
        this.vnpCreateDate = vnpCreateDate;
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
}
