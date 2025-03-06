package dal;

import model.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.FeedbackChart;
import model.SaleChart;
import model.User;

public class OrderDAO extends DBContext {

    public int getOrderIdByVNP(String vnp_TxnRef) {
        String sql = """
                     select order_id
                     from Orders
                     where vnp_TxnRef = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, vnp_TxnRef);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

    public Order getOrderBySaleIdAndOrderId(int sid, int ordId) {
        String sql = """
                     select o.*,pm.payment_method_name,ps.payment_status_name,os.order_status_name
                       from Orders o
                       left join Payment_Methods pm on pm.payment_method_id = o.payment_method_id
                       left join Payment_Status ps on ps.payment_status_id = o.payment_status_id
                       left join Order_Status os on os.order_status_id = o.order_status_id
                       WHERE [salerId] = ? and [order_id] = ?""";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sid);
            pre.setInt(2, ordId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("order_id");
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiveDate = rs.getString("receive_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");

                String paymentMethodName = rs.getString("payment_method_name");
                String paymentStatusName = rs.getString("payment_status_name");
                String orderStatusName = rs.getString("order_status_name");
                return new Order(oid, cid, orderedDate, receiveDate, receiverName, phone, email, address, wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee, voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate, paymentStatusId, orderStatusId, shippingCode, saleId, paymentMethodName, paymentStatusName, orderStatusName);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Order getOrderByCustomerIdAndOrderId(int cusId, int ordId) {
        String sql = """
                     select o.*,pm.payment_method_name,ps.payment_status_name,os.order_status_name
                       from Orders o
                       left join Payment_Methods pm on pm.payment_method_id = o.payment_method_id
                       left join Payment_Status ps on ps.payment_status_id = o.payment_status_id
                       left join Order_Status os on os.order_status_id = o.order_status_id
                       WHERE [customer_id] = ? and [order_id] = ?""";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cusId);
            pre.setInt(2, ordId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("order_id");
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiveDate = rs.getString("receive_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");

                String paymentMethodName = rs.getString("payment_method_name");
                String paymentStatusName = rs.getString("payment_status_name");
                String orderStatusName = rs.getString("order_status_name");
                return new Order(oid, cid, orderedDate, receiveDate, receiverName, phone, email, address, wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee, voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate, paymentStatusId, orderStatusId, shippingCode, saleId, paymentMethodName, paymentStatusName, orderStatusName);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Order> getOrderByCustomer(int id) {
        List<Order> oList = new ArrayList<>();
        String sql = """
                     select o.*,pm.payment_method_name,ps.payment_status_name,os.order_status_name
                       from Orders o
                       left join Payment_Methods pm on pm.payment_method_id = o.payment_method_id
                       left join Payment_Status ps on ps.payment_status_id = o.payment_status_id
                       left join Order_Status os on os.order_status_id = o.order_status_id
                       WHERE [customer_id] = ?
                        order by o.ordered_date desc""";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("order_id");
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");

                String paymentMethodName = rs.getString("payment_method_name");
                String paymentStatusName = rs.getString("payment_status_name");
                String orderStatusName = rs.getString("order_status_name");
                Order order = new Order(oid, cid, orderedDate, receiverName, phone, email, address, wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee, voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate, paymentStatusId, orderStatusId, shippingCode, saleId, paymentMethodName, paymentStatusName, orderStatusName);
                oList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return oList;
    }

    public boolean updateOrderStatus(int orderId, int orderStatusId) {
        String sql = """
                     UPDATE [dbo].[Orders]
                         SET 
                            [order_status_id] = ?
                       WHERE [order_id] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderStatusId);
            ps.setInt(2, orderId);

            int n = ps.executeUpdate();

            return n > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateOrderStatus(String vnp_TxnRef, int orderStatusId) {
        String sql = """
                     UPDATE [dbo].[Orders]
                         SET 
                            [order_status_id] = ?
                       WHERE [vnp_TxnRef] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderStatusId);
            ps.setString(2, vnp_TxnRef);

            int n = ps.executeUpdate();

            return n > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updatePaymentStatus(int orderId, int paymentStatusId) {
        String sql = """
                     UPDATE [dbo].[Orders]
                        SET 
                           [payment_status_id] = ?
                      WHERE [order_id] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, paymentStatusId);
            ps.setInt(2, orderId);

            int n = ps.executeUpdate();

            return n > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updatePaymentStatus(String vnp_TxnRef, int paymentStatusId) {
        String sql = """
                     UPDATE [dbo].[Orders]
                        SET 
                           [payment_status_id] = ?
                      WHERE [vnp_TxnRef] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, paymentStatusId);
            ps.setString(2, vnp_TxnRef);

            int n = ps.executeUpdate();

            return n > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateVnPayField(int orderId, String vnp_TxnRef, String vnp_CreateDate) {
        String sql = """
                     UPDATE [dbo].[Orders]
                        SET 
                           [vnp_TxnRef] = ?
                           ,[vnp_CreateDate] = ?
                      WHERE [order_id] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, vnp_TxnRef);
            ps.setString(2, vnp_CreateDate);
            ps.setInt(3, orderId);

            int n = ps.executeUpdate();

            return n > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int insertOrder(Order order) {

        String sql = """
                     INSERT INTO [dbo].[Orders]
                                           ([customer_id]
                                           ,[ordered_date]
                                           ,[receiver_name]
                                           ,[phone]
                                           ,[email]
                                           ,[address]
                                           ,[ward_code]
                                           ,[ward_name]
                                           ,[district_id]
                                           ,[district_name]
                                           ,[province_id]
                                           ,[province_name]
                                           ,[total_price]
                                           ,[shipping_fee]
                                           ,[voucher_id]
                                           ,[voucher_percent]
                                           ,[total_amount]
                                           ,[total_gram]
                                           ,[payment_method_id]
                                           ,[vnp_TxnRef]
                                           ,[vnp_CreateDate]
                                           ,[payment_status_id]
                                           ,[order_status_id]
                                           ,[shipping_code])
                                           
                                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getCustomerId());
            ps.setString(2, order.getOrderedDate());
            ps.setString(3, order.getReceiverName());
            ps.setString(4, order.getPhone());
            ps.setString(5, order.getEmail());
            ps.setString(6, order.getAddress());
            ps.setString(7, order.getWardCode());
            ps.setString(8, order.getWardName());
            ps.setInt(9, order.getDistrictId());
            ps.setString(10, order.getDistrictName());
            ps.setInt(11, order.getProvinceId());
            ps.setString(12, order.getProvinceName());
            ps.setInt(13, order.getTotalPrice());
            ps.setInt(14, order.getShippingFee());
            ps.setInt(15, order.getVoucherId());
            ps.setInt(16, order.getVoucherPercent());
            ps.setInt(17, order.getTotalAmount());
            ps.setInt(18, order.getTotalGram());
            ps.setInt(19, order.getPaymentMethodId());
            ps.setString(20, null);
            ps.setString(21, null);
            ps.setInt(22, order.getPaymentStatusId());
            ps.setInt(23, order.getOrderStatusId());
            ps.setString(24, null);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public List<Order> getAllOrder() {
        List<Order> oList = new ArrayList<>();
        String sql = "select * from Orders";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("order_id");
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");
                Order order = new Order(oid, cid, orderedDate, receiverName, phone, email, address,
                        wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee,
                        voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate,
                        paymentStatusId, orderStatusId, shippingCode, saleId);
                oList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return oList;
    }

    public Order getOrder(int oid) {
        Order order = new Order();
        String sql = "select * from Orders "
                + "where order_id=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, oid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");
                String receiveDate = rs.getString("receive_date");
                order = new Order(oid, cid, orderedDate, receiveDate, receiverName, phone, email, address,
                        wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee,
                        voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate,
                        paymentStatusId, orderStatusId, shippingCode, saleId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return order;
    }

    public Order getOrderById(int oid) {
        Order order = new Order();
        String sql = "select * from Orders "
                + "where order_id=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, oid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");
                order = new Order(cid, orderedDate, receiverName, phone, email, address,
                        wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee,
                        voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate,
                        paymentStatusId, orderStatusId, shippingCode, saleId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return order;
    }

    public List<SaleChart> getSucsessOnTotalOrder(int saleId, LocalDate startDate, long days) {
        List<SaleChart> sList = new ArrayList<>();

        String sql = "Select os.order_status_id, os.order_status_name, count(order_id) as Total_Order\n"
                + "from Order_Status as os\n"
                + "right join Orders as o on os.order_status_id = o.order_status_id\n"
                + "where ordered_date>= ? and ordered_date <= ?\n"
                + "group by os.order_status_id,os.order_status_name\n"
                + "order by os.order_status_id";

        if (saleId != 0) {
            sql = "Select os.order_status_id, os.order_status_name, count(order_id) as Total_Order\n"
                    + "from Order_Status as os\n"
                    + "right join Orders as o on os.order_status_id = o.order_status_id\n"
                    + "where ordered_date>= ? and ordered_date <= ?\n"
                    + "and salerId = " + saleId + " "
                    + "group by os.order_status_id,os.order_status_name\n"
                    + "order by os.order_status_id";
        }

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, startDate + "");
            pre.setString(2, startDate.plusDays(days) + "");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String name = rs.getString("order_status_name");
                int value = rs.getInt("Total_Order");
                SaleChart saleChart = new SaleChart(name, value);
                sList.add(saleChart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sList;
    }

    public List<SaleChart> getNumberOfOrderByDay(int saleId, LocalDate startDate, long days) {
        List<SaleChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select count(order_id) as Total_number from Orders\n"
                + "where Year(ordered_date) =? and MONTH(ordered_date)=? and DAY(ordered_date)=?";

        if (saleId != 0) {
            sql = "select count(order_id) as Total_number from Orders\n"
                    + "where Year(ordered_date) =? and MONTH(ordered_date)=? and DAY(ordered_date)=? and salerid = " + saleId;
        }

        for (int i = 0; i <= days; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
                pre.setInt(1, date.getYear());
                pre.setInt(2, date.getMonthValue());
                pre.setInt(3, date.getDayOfMonth());

                ResultSet rs = pre.executeQuery();
                while (rs.next()) {

                    String fdate = dtf.format(date);
                    int value = rs.getInt("Total_number");
                    SaleChart saleChart = new SaleChart(fdate, value);
                    sList.add(saleChart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return sList;
    }

    public List<SaleChart> getTotalRevenueByDay(int saleId, LocalDate startDate, long days) {
        List<SaleChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select sum(total_amount) as Total_Price\n"
                + "from Orders\n"
                + "where Year(ordered_date) =? and MONTH(ordered_date)=? and DAY(ordered_date)=? and order_status_id = 4";

        if (saleId != 0) {
            sql = "select sum(total_amount) as Total_Price \n"
                    + "from Orders\n"
                    + "where Year(ordered_date) =? and MONTH(ordered_date)=? and DAY(ordered_date)=? and order_status_id = 4 and salerid = " + saleId;
        }

        for (int i = 0; i <= days; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
//                pre.setString(1, date.getYear() + "");
//                pre.setString(2, date.getMonth()+ "");
//                pre.setString(3, date.getDayOfMonth()+ "");
                pre.setInt(1, date.getYear());
                pre.setInt(2, date.getMonthValue());
                pre.setInt(3, date.getDayOfMonth());
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {

                    String fdate = dtf.format(date);
                    int value = rs.getInt("Total_Price");
                    SaleChart saleChart = new SaleChart(fdate, value);
                    sList.add(saleChart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return sList;
    }

    public List<SaleChart> getRevenueAccumulateByDay(int saleId, LocalDate startDate, long days) {
        List<SaleChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select sum(total_amount) as Total_Price\n"
                + "from Orders\n"
                + "where ordered_date <= ? and order_status_id = 4";

        if (saleId != 0) {
            sql = "select sum(total_amount) as Total_Price \n"
                    + "from Orders\n"
                    + "where ordered_date <= ? and order_status_id = 4 and salerid = " + saleId;
        }

        for (int i = 0; i <= days; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
                pre.setString(1, date + "");

                ResultSet rs = pre.executeQuery();

                while (rs.next()) {

                    String fdate = dtf.format(date);
                    int value = rs.getInt("Total_Price");
                    SaleChart saleChart = new SaleChart(fdate, value);
                    sList.add(saleChart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return sList;
    }

    public int getTotalOrder(int saleId, LocalDate startDate, long days) {

        int total = 0;

        String sql = "select count(*) as Total_Order\n"
                + "from Orders where ordered_date>= ? and ordered_date <= ?";

        if (saleId != 0) {
            sql = "select count(*) as Total_Order\n"
                    + "from Orders where ordered_date>= ? and ordered_date <= ? and salerid = " + saleId;
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, startDate + "");
            pre.setString(2, startDate.plusDays(days) + "");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                total = rs.getInt("Total_Order");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }

    public List<Order> getOrderPending(String startDate, String endDate) {
        List<Order> oList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
                 select o.*, pm.payment_method_name, ps.payment_status_name, os.order_status_name
                   from Orders o
                   left join Payment_Methods pm on pm.payment_method_id = o.payment_method_id
                   left join Payment_Status ps on ps.payment_status_id = o.payment_status_id
                   left join Order_Status os on os.order_status_id = o.order_status_id
                   WHERE o.order_status_id = 1
                 """);

        if (startDate != null && endDate != null) {
            sql.append(" AND o.ordered_date BETWEEN ? AND ?");
        }
        sql.append(" order by o.ordered_date desc");

        try {
            PreparedStatement pre = connection.prepareStatement(sql.toString());
            if (startDate != null && endDate != null) {
                pre.setString(1, startDate);
                pre.setString(2, endDate);
            }
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("order_id");
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");

                String paymentMethodName = rs.getString("payment_method_name");
                String paymentStatusName = rs.getString("payment_status_name");
                String orderStatusName = rs.getString("order_status_name");

                Order order = new Order(oid, cid, orderedDate, receiverName, phone, email, address, wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee, voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate, paymentStatusId, orderStatusId, shippingCode, saleId, paymentMethodName, paymentStatusName, orderStatusName);
                oList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return oList;
    }

    public List<Order> getOrderBySale(int id, String begin, String end) {
        List<Order> oList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
                     select o.*,pm.payment_method_name,ps.payment_status_name,os.order_status_name
                       from Orders o
                       left join Payment_Methods pm on pm.payment_method_id = o.payment_method_id
                       left join Payment_Status ps on ps.payment_status_id = o.payment_status_id
                       left join Order_Status os on os.order_status_id = o.order_status_id
                       WHERE [salerId] = ?
                     """);
        if (begin != null && end != null) {
            sql.append(" AND o.ordered_date BETWEEN ? AND ?");
        }
        sql.append(" order by o.ordered_date desc");

        try {
            PreparedStatement pre = connection.prepareStatement(sql.toString());
            pre.setInt(1, id);
            if (begin != null && end != null) {
                pre.setString(2, begin);
                pre.setString(3, end);
            }
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("order_id");
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");

                String paymentMethodName = rs.getString("payment_method_name");
                String paymentStatusName = rs.getString("payment_status_name");
                String orderStatusName = rs.getString("order_status_name");
                Order order = new Order(oid, cid, orderedDate, receiverName, phone, email, address, wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee, voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate, paymentStatusId, orderStatusId, shippingCode, saleId, paymentMethodName, paymentStatusName, orderStatusName);
                oList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return oList;
    }

    public List<SaleChart> getRevenueAccumulateByMonth(int year) {
        Map<String, Integer> months = new LinkedHashMap<>();
        months.put("Tháng 1", 1);
        months.put("Tháng 2", 2);
        months.put("Tháng 3", 3);
        months.put("Tháng 4", 4);
        months.put("Tháng 5", 5);
        months.put("Tháng 6", 6);
        months.put("Tháng 7", 7);
        months.put("Tháng 8", 8);
        months.put("Tháng 9", 9);
        months.put("Tháng 10", 10);
        months.put("Tháng 11", 11);
        months.put("Tháng 12", 12);

        List<SaleChart> sList = new ArrayList<>();
        String sql = "select sum(total_amount) as Total_Price "
                + "from Orders "
                + "where Month(ordered_date) = ? and Year(ordered_date)=? and order_status_id = 4";

        for (Map.Entry<String, Integer> entry : months.entrySet()) {
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1, entry.getValue()); // Giá trị của tháng từ Map
                pre.setInt(2, year);
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    String label = entry.getKey(); // Tên tháng làm nhãn
                    int value = rs.getInt("Total_Price");
                    SaleChart saleChart = new SaleChart(label, value); // Dùng label thay cho fdate
                    sList.add(saleChart);
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sList;

    }

    public boolean updatePendingOrderStatus(int uid, int orderId) {
        String sql = "UPDATE Orders SET order_status_id = 2, salerId = ? WHERE order_id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, uid);
            pre.setInt(2, orderId);
            int n = pre.executeUpdate();
            return n > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<SaleChart> getNumberOfOrderByMonth(int year) {
        Map<String, Integer> months = new LinkedHashMap<>();
        months.put("Tháng 1", 1);
        months.put("Tháng 2", 2);
        months.put("Tháng 3", 3);
        months.put("Tháng 4", 4);
        months.put("Tháng 5", 5);
        months.put("Tháng 6", 6);
        months.put("Tháng 7", 7);
        months.put("Tháng 8", 8);
        months.put("Tháng 9", 9);
        months.put("Tháng 10", 10);
        months.put("Tháng 11", 11);
        months.put("Tháng 12", 12);

        List<SaleChart> sList = new ArrayList<>();
        String sql = "select count(order_id) as Total_number from Orders\n"
                + "                where Month(ordered_date) = ? and Year(ordered_date)=? and order_status_id = 4";
        for (Map.Entry<String, Integer> entry : months.entrySet()) {
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1, entry.getValue()); // Giá trị của tháng từ Map
                pre.setInt(2, year);
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    String label = entry.getKey(); // Tên tháng làm nhãn
                    int value = rs.getInt("Total_number");
                    SaleChart saleChart = new SaleChart(label, value); // Dùng label thay cho fdate
                    sList.add(saleChart);
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sList;
    }

    public List<Order> getOrderByFilterDate(String startDate, String endDate) {
        List<Order> oList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
                 select o.*, pm.payment_method_name, ps.payment_status_name, os.order_status_name
                   from Orders o
                   left join Payment_Methods pm on pm.payment_method_id = o.payment_method_id
                   left join Payment_Status ps on ps.payment_status_id = o.payment_status_id
                   left join Order_Status os on os.order_status_id = o.order_status_id
                 """);

        if (startDate != null && endDate != null) {
            sql.append(" where o.ordered_date BETWEEN ? AND ?");
        }
        sql.append(" order by o.ordered_date desc");

        try {
            PreparedStatement pre = connection.prepareStatement(sql.toString());
            if (startDate != null && endDate != null) {
                pre.setString(1, startDate);
                pre.setString(2, endDate);
            }
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("order_id");
                int cid = rs.getInt("customer_id");
                String orderedDate = rs.getString("ordered_date");
                String receiverName = rs.getString("receiver_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String wardCode = rs.getString("ward_code");
                String wardName = rs.getString("ward_name");
                int districtId = rs.getInt("district_id");
                String districtName = rs.getString("district_name");
                int provinceId = rs.getInt("province_id");
                String provinceName = rs.getString("province_name");
                int totalPrice = rs.getInt("total_price");
                int shippingFee = rs.getInt("shipping_fee");
                int voucherId = rs.getInt("voucher_id");
                int voucherPercent = rs.getInt("voucher_percent");
                int totalAmount = rs.getInt("total_amount");
                int totalGram = rs.getInt("total_gram");
                int paymentMethodId = rs.getInt("payment_method_id");
                String vnpTxnRef = rs.getString("vnp_TxnRef");
                String vnpCreateDate = rs.getString("vnp_CreateDate");
                int paymentStatusId = rs.getInt("payment_status_id");
                int orderStatusId = rs.getInt("order_status_id");
                String shippingCode = rs.getString("shipping_code");
                int saleId = rs.getInt("salerId");

                String paymentMethodName = rs.getString("payment_method_name");
                String paymentStatusName = rs.getString("payment_status_name");
                String orderStatusName = rs.getString("order_status_name");

                Order order = new Order(oid, cid, orderedDate, receiverName, phone, email, address, wardCode, wardName, districtId, districtName, provinceId, provinceName, totalPrice, shippingFee, voucherId, voucherPercent, totalAmount, totalGram, paymentMethodId, vnpTxnRef, vnpCreateDate, paymentStatusId, orderStatusId, shippingCode, saleId, paymentMethodName, paymentStatusName, orderStatusName);
                oList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return oList;
    }

    public List<SaleChart> getNumberStatusOrderByMonth(int month, int year) {
        List<SaleChart> sList = new ArrayList<>();
        String sql = "Select  os.order_status_name, count(order_id) as Total_Order\n"
                + "                from Order_Status as os\n"
                + "                right join Orders as o on os.order_status_id = o.order_status_id\n"
                + "                where Month(ordered_date) = ? and Year(ordered_date)=?\n"
                + "                group by os.order_status_id,os.order_status_name\n"
                + "                order by os.order_status_id";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, month);
            pre.setInt(2, year);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String name = rs.getString("order_status_name");
                int value = rs.getInt("Total_Order");
                sList.add(new SaleChart(name, value));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sList;
    }

    public int getTotalOrderInMonth(int month, int year) {
        int count = 0;
        String sql = "select count(order_id) as Total_number from Orders\n"
                + "where Month(ordered_date) = ? and Year(ordered_date)=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, month);
            pre.setInt(2, year);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                count = rs.getInt("Total_number");
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public List<SaleChart> getTotalByBrandInMonth(int month, int year) {
        List<SaleChart> sList = new ArrayList<>();
        String sql = "SELECT b.brand_name, Sum(od.quantity) AS total_order\n"
                + "                FROM Orders o\n"
                + "                INNER JOIN Order_Details od ON o.order_id = od.order_id\n"
                + "                INNER JOIN Products p ON od.product_id = p.product_id\n"
                + "                INNER JOIN Brands b ON p.brand_id = b.brand_id\n"
                + "                where Month(o.ordered_date) = ? and Year(o.ordered_date)= ? and o.order_status_id=4\n"
                + "                GROUP BY b.brand_name";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, month); // Giá trị của tháng từ Map
            pre.setInt(2, year);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String label = rs.getString("brand_name"); // Tên tháng làm nhãn
                int value = rs.getInt("total_order");
                SaleChart saleChart = new SaleChart(label, value); // Dùng label thay cho fdate
                sList.add(saleChart);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sList;
    }

    public boolean unsignSale(int orderId) {
        String sql = "UPDATE Orders SET salerId = null WHERE order_id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, orderId);
            int n = pre.executeUpdate();
            return n > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<SaleChart> getAmmountPerDay(LocalDate startDate, LocalDate endDate) {
        List<SaleChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select Sum(total_amount) as totalamount\n"
                + "from Orders \n"
                + "where YEAR(ordered_date)=? AND MONTH(ordered_date)=? AND DAY(ordered_date)=? AND order_status_id=? ";

        int daybetween = (int) ChronoUnit.DAYS.between(startDate, endDate);

        for (int i = 0; i <= daybetween; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
                pre.setString(1, date.getYear() + "");
                pre.setString(2, date.getMonthValue() + "");
                pre.setString(3, date.getDayOfMonth() + "");
                pre.setInt(4, 4);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {

                    String fdate = dtf.format(date);
                    int value = rs.getInt("totalamount");
                    SaleChart saleChart = new SaleChart(fdate, value);
                    sList.add(saleChart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sList;
    }

    public List<SaleChart> getTotalAmountByDay(LocalDate startDate, LocalDate endDate) {
        List<SaleChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select Sum(total_amount) as totalamount\n"
                + "from Orders \n"
                + "where ordered_date <= ? AND order_status_id= ? ";

        int daybetween = (int) ChronoUnit.DAYS.between(startDate, endDate);

        for (int i = 0; i <= daybetween; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
                pre.setString(1, date + "");

                pre.setInt(2, 4);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {

                    String fdate = dtf.format(date);
                    int value = rs.getInt("totalamount");
                    SaleChart saleChart = new SaleChart(fdate, value);
                    sList.add(saleChart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sList;
    }

    public boolean updateReceivedDate(int orderId, String receiveDate) {
        String sql = """
                     UPDATE [dbo].[Orders]
                          SET 
                             [receive_date] = ?                       
                        WHERE order_id = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, receiveDate);
            ps.setInt(2, orderId);

            int n = ps.executeUpdate();

            return n > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateShippingCode(int orderId, String shipping_code) {
        String sql = """
                     UPDATE [dbo].[Orders]
                         SET 
                            [shipping_code] = ?
                       WHERE [order_id] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, shipping_code);
            ps.setInt(2, orderId);

            int n = ps.executeUpdate();

            return n > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<FeedbackChart> getTotalRatingByBrandInMonth(int month, int year) {
        List<FeedbackChart> sList = new ArrayList<>();
        String sql = "select b.brand_name, AVG(f.rating * 1.0) as average from Feedbacks f \n" +
                        "join Orders o on f.order_id=o.order_id \n" +
                        "join Products p on f.product_id=p.product_id\n" +
                        "join Brands b on b.brand_id=p.brand_id\n" +
                        "where Month(o.ordered_date) =? and YEAR(o.ordered_date)=? and o.order_status_id=4\n" +
                        "group by b.brand_name";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, month); // Giá trị của tháng từ Map
            pre.setInt(2, year);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String label = rs.getString("brand_name"); // Tên tháng làm nhãn
                float value = rs.getFloat("average");
                FeedbackChart fChart = new FeedbackChart(label, value); // Dùng label thay cho fdate
                sList.add(fChart);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sList;
    }
    public static void main(String[] args) {
        OrderDAO odao = new OrderDAO();
//          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        List<SaleChart> o = odao.getTotalAmountByDay(LocalDate.parse("2024-09-01")   , LocalDate.now() );
//        System.out.println(o);

        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDateString = currentDate.format(formatter);
        // Các giá trị cần thiết để tạo đối tượng Order
        int customerId = 1;                      // ID của khách hàng
        String orderedDate = currentDateString; // Ngày đặt hàng
        String receiverName = "Nguyễn Văn A";    // Tên người nhận
        String phone = "0123456789";              // Số điện thoại người nhận
        String email = "nguyenvana@example.com"; // Email người nhận
        String address = "123 Đường ABC";        // Địa chỉ giao hàng
        String wardCode = "001";                  // Mã phường
        String wardName = "Phường 1";             // Tên phường
        int districtId = 1;                       // ID quận
        String districtName = "Quận 1";           // Tên quận
        int provinceId = 1;                       // ID tỉnh
        String provinceName = "TP.HCM";           // Tên tỉnh
        int totalPrice = 1000000;                 // Tổng giá tiền
        int shippingFee = 50000;                  // Phí vận chuyển
        int voucherId = 1;                        // ID voucher (nếu không có thì để 0)
        int voucherPercent = 0;                   // Phần trăm giảm giá (nếu không có thì để 0)
        int totalAmount = 950000;                  // Tổng số tiền sau khi giảm giá
        int totalGram = 1000;                     // Tổng trọng lượng (gram)
        int paymentMethodId = 1;                  // ID phương thức thanh toán
        int paymentStatusId = 1;                  // ID trạng thái thanh toán
        int orderStatusId = 1;                    // ID trạng thái đơn hàng

        // Tạo đối tượng Order
        Order newOrder = new Order(
                customerId,
                orderedDate,
                receiverName,
                phone,
                email,
                address,
                wardCode,
                wardName,
                districtId,
                districtName,
                provinceId,
                provinceName,
                totalPrice,
                shippingFee,
                voucherId,
                voucherPercent,
                totalAmount,
                totalGram,
                paymentMethodId,
                paymentStatusId,
                orderStatusId
        );

        if (odao.insertOrder(newOrder) != -1) {
            System.out.println("success");
        }

    }
}
