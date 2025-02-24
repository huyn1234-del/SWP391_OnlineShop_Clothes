package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import dao.OrderDAO;
import model.Order;

public class OrderList extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    OrderDAO orderDAO = new OrderDAO();

    // Lấy thông tin từ request
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");
    int page = 1;
    String pageParam = request.getParameter("page");
    if (pageParam != null && !pageParam.isEmpty()) {
        page = Integer.parseInt(pageParam);
    }

    int PAGE_SIZE = 10; // Số đơn hàng mỗi trang
    int offset = (page - 1) * PAGE_SIZE;

    List<Order> orders;
    int totalOrders;

    // Kiểm tra nếu có lọc theo ngày
    if (startDate != null && endDate != null && !startDate.isEmpty() && !endDate.isEmpty()) {
        orders = orderDAO.getOrdersByDate(startDate, endDate, offset, PAGE_SIZE);
        totalOrders = orderDAO.getTotalOrdersByDate(startDate, endDate);
    } else {
        // Nếu không lọc, lấy toàn bộ đơn hàng
        orders = orderDAO.getOrdersByPage(page, PAGE_SIZE);
        totalOrders = orderDAO.getTotalOrderCount();
    }

    // Tính tổng số trang
    int totalPages = (int) Math.ceil((double) totalOrders / PAGE_SIZE);

    // Gửi dữ liệu tới JSP
    request.setAttribute("orders", orders);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);
    request.setAttribute("startDate", startDate);
    request.setAttribute("endDate", endDate);

    // Forward tới JSP
    RequestDispatcher dispatcher = request.getRequestDispatcher("OrderList.jsp");
    dispatcher.forward(request, response);
}
}
