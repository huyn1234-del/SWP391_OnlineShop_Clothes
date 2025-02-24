
package dao;
import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Order;

public class OrderDAO {
    private Connection connection;

    public OrderDAO() {
        connection = new DBContext().connection;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, customer_id, ordered_date, receive_date, receiver_name, phone, email, " +
                     "address, total_price, shipping_fee, voucher_id, voucher_percent, total_amount, " +
                     "payment_method_id, payment_status_id, order_status_id, shipping_code, salerId " +
                     "FROM Orders";

        try {
            Connection conn = new DBContext().connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderedDate(rs.getString("ordered_date"));
                order.setReceiveDate(rs.getString("receive_date"));
                order.setReceiverName(rs.getString("receiver_name"));
                order.setPhone(rs.getString("phone"));
                order.setEmail(rs.getString("email"));
                order.setAddress(rs.getString("address"));
                order.setTotalPrice(rs.getInt("total_price"));
                order.setShippingFee(rs.getInt("shipping_fee"));
                order.setVoucherId(rs.getInt("voucher_id"));
                order.setVoucherPercent(rs.getInt("voucher_percent"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setPaymentMethodId(rs.getInt("payment_method_id"));
                order.setPaymentStatusId(rs.getInt("payment_status_id"));
                order.setOrderStatusId(rs.getInt("order_status_id"));
                order.setShippingCode(rs.getString("shipping_code"));
                order.setSaleId(rs.getInt("salerId"));

                orders.add(order);
            }
            System.out.println("✅ Lấy được " + orders.size() + " đơn hàng từ database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

public List<Order> getOrdersByPage(int page, int pageSize) {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM Orders ORDER BY order_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, (page - 1) * pageSize); // Offset
        ps.setInt(2, pageSize); // Page size
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setOrderedDate(rs.getString("ordered_date"));
            order.setTotalPrice(rs.getInt("total_price"));
            order.setPaymentMethodName(rs.getString("payment_method_id")); // Kiểm tra tên cột
            order.setPaymentStatusName(rs.getString("payment_status_id"));
            order.setOrderStatusName(rs.getString("order_status_id"));

            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}
public List<Order> getOrdersByDate(String startDate, String endDate, int offset, int limit) {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM Orders WHERE ordered_date BETWEEN ? AND ? ORDER BY ordered_date ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        ps.setInt(3, offset);
        ps.setInt(4, limit);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setOrderedDate(rs.getString("ordered_date"));
            order.setTotalPrice(rs.getInt("total_price"));
            order.setPaymentMethodName(rs.getString("payment_method_id"));
            order.setPaymentStatusName(rs.getString("payment_status_id"));
            order.setOrderStatusName(rs.getString("order_status_id"));
            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}

public int getTotalOrdersByDate(String startDate, String endDate) {
    String sql = "SELECT COUNT(*) FROM Orders WHERE ordered_date BETWEEN ? AND ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}


public int getTotalOrderCount() {
    String sql = "SELECT COUNT(*) FROM Orders";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}
public Order getOrderById(int orderId) {
    Order order = null;
    String sql = "SELECT * FROM Orders WHERE order_id = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setOrderedDate(rs.getString("ordered_date"));
            order.setReceiveDate(rs.getString("receive_date"));
            order.setReceiverName(rs.getString("receiver_name"));
            order.setPhone(rs.getString("phone"));
            order.setEmail(rs.getString("email"));
            order.setAddress(rs.getString("address"));
            order.setTotalPrice(rs.getInt("total_price"));
            order.setShippingFee(rs.getInt("shipping_fee"));
            order.setVoucherPercent(rs.getInt("voucher_percent"));
            order.setTotalAmount(rs.getInt("total_amount"));
            order.setPaymentMethodId(rs.getInt("payment_method_id"));
            order.setPaymentStatusId(rs.getInt("payment_status_id"));
            order.setOrderStatusId(rs.getInt("order_status_id"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return order;
}

}
