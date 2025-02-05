/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Order;
import model.OrderStatus;

/**
 *
 * @author user
 */
public class ViewOrderStatusAdmin extends HttpServlet {
   


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        AdminDAO adao = new AdminDAO();
        List<Order> listO = adao.getAllOrder();
        
    Map<Integer, String> orderStatusMap = new HashMap<>();

    for (Order o : listO) {
        OrderStatus status = adao.getOrderStatusByID(String.valueOf(o.getOrderStatusId()));
        if (status != null) {
            orderStatusMap.put(o.getOrderStatusId(), status.getOrderStatusName());
        } else {
            orderStatusMap.put(o.getOrderStatusId(), "Không xác định");
        }
    }
        request.setAttribute("orderStatusMap", orderStatusMap);
        request.setAttribute("listO", listO);
        request.getRequestDispatcher("OrderStatusAdmin.jsp").forward(request , response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
