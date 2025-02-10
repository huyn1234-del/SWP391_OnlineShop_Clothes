package dao;
import java.util.List;
import model.Order;

public class TestOrderController {
    public static void main(String[] args) {
        // Tạo đối tượng OrderDAO
        OrderDAO orderDAO = new OrderDAO();

        // Lấy danh sách đơn hàng
        List<Order> orders = orderDAO.getAllOrders();

        // Kiểm tra dữ liệu lấy được
        System.out.println("✅ Tổng số đơn hàng: " + orders.size());

        if (orders.isEmpty()) {
            System.out.println("❌ Không có đơn hàng nào trong database!");
        } else {
            for (Order order : orders) {
                System.out.println("🛒 Order ID: " + order.getOrderId()
                        + " | Ngày đặt: " + order.getOrderedDate()
                        + " | Tổng tiền: " + order.getTotalPrice() + " đ");
            }
        }
    }
}
