package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import model.Order;
import java.util.List;

public class OrderDetail extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int orderId = Integer.parseInt(request.getParameter("orderId")); // Lấy `orderId` từ request

    // Lấy thông tin đơn hàng
    OrderDAO orderDAO = new OrderDAO();
    Order order = orderDAO.getOrderById(orderId);

    // Lấy chi tiết sản phẩm trong đơn hàng
    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    List<model.OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderId);

    // Gửi dữ liệu tới JSP
    request.setAttribute("order", order);
    request.setAttribute("orderDetails", orderDetails);

    RequestDispatcher dispatcher = request.getRequestDispatcher("OrderDetail.jsp");
    dispatcher.forward(request, response);
}

}
