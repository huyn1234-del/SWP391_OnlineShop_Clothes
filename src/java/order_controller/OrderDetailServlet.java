
package order_controller;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Order;
import model.User;
import model.OrderDetail;


@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/orderdetail"})
public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        User account = (User) sesion.getAttribute("account");
        OrderDAO orderDAO = new OrderDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        
        try {

            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = orderDAO.getOrderBySaleIdAndOrderId(account.getUser_id(), orderId);

            request.setAttribute("order", order);

            List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailByOrderId(orderId);

            request.setAttribute("orderDetails", orderDetails);

            
            request.getRequestDispatcher("/management/order-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
             request.getRequestDispatcher("/management/order-detail.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
