package order_controller;

import dal.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CancelOrder", urlPatterns = {"/cancelorder"})
public class CancelOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            OrderDAO orderDAO = new OrderDAO();
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            boolean check = orderDAO.updateOrderStatus(orderId, 5); // Giả sử 5 là trạng thái "Hủy đơn"

            if (check) {
                session.setAttribute("notify", "Đơn hàng đã được hủy thành công.");
            } else {
                session.setAttribute("notify", "Hủy đơn hàng thất bại. Vui lòng thử lại sau.");
            }

            response.sendRedirect(request.getContextPath() + "/orderdetail?orderId=" + orderId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
