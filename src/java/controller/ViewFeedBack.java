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
import model.FeedBack;
import model.Order;
import model.Product;

/**
 *
 * @author user
 */
public class ViewFeedBack extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminDAO adao = new AdminDAO();
        List<FeedBack> listF = adao.getAllFeedBacks();
        Map<Integer, String> productNamesMap = new HashMap<>();
        for (FeedBack feedback : listF) {
            Product product = adao.getProductByID(feedback.getProductId());
            if (product != null) {
                productNamesMap.put(feedback.getProductId(), product.getProduct_name());
            } else {
                productNamesMap.put(feedback.getProductId(), "Không xác định");
            }
        }
        
        
        System.out.println(productNamesMap);
        request.setAttribute("productNamesMap", productNamesMap);
        request.setAttribute("listF", listF);
        request.getRequestDispatcher("ViewFeedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
