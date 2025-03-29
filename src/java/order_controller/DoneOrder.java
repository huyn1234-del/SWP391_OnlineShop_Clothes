
package order_controller;

import dal.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "DoneOrder", urlPatterns = {"/doneorder"})
public class DoneOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            HttpSession session =request.getSession();
            OrderDAO orderDAO = new OrderDAO();
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            boolean check = orderDAO.updateOrderStatus(orderId, 4);
            
            if(check){
                session.setAttribute("notify", "Cập nhật trạng thái thành công.");
            }else{
                session.setAttribute("notify", "Cập nhật trạng thái thất bại. Vui lòng thử lại sau");
            }
            

            response.sendRedirect(request.getContextPath() + "/orderdetail?orderId=" + orderId);
        } catch (NumberFormatException e) {
            
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

