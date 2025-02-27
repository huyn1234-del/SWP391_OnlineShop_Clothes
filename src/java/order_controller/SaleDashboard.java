
package order_controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;

public class SaleDashboard extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tiến hành điều hướng tới SaleDashboard.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/management/sale-dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
