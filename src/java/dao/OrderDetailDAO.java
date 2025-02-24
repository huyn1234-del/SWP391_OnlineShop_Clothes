package dao;
import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;

public class OrderDetailDAO {
    private final Connection connection;

    public OrderDetailDAO() {
        // Khởi tạo connection từ DBContext
        this.connection = new DBContext().connection;
    }

public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
    List<OrderDetail> orderDetails = new ArrayList<>();
    String sql = "SELECT od.order_id, od.product_id, p.product_name, od.size_id, s.size_name, " +
                 "od.quantity, od.unit_price, (od.quantity * od.unit_price) AS total_price " +
                 "FROM OrderDetails od " +
                 "JOIN Product p ON od.product_id = p.product_id " +
                 "JOIN Size s ON od.size_id = s.size_id " +
                 "WHERE od.order_id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, orderId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrderId(rs.getInt("order_id"));
                detail.setProductId(rs.getInt("product_id"));
                detail.setProductName(rs.getString("product_name"));
                detail.setSizeId(rs.getInt("size_id"));
                detail.setSizeName(rs.getString("size_name"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnitPrice(rs.getInt("unit_price"));
                detail.setTotalPrice(rs.getInt("total_price")); // Tính tổng giá
                orderDetails.add(detail);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return orderDetails;
}

}

