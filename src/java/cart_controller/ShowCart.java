/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cart_controller;

import dal.ProductSizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Cart;
import utils.Constants;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Cart", urlPatterns = {"/cart"})
public class ShowCart extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductSizeDAO daoProductSize = new ProductSizeDAO();
        
        Cookie[] cookies = request.getCookies();

        String txt = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Constants.COOKIE_CART)) {
                txt = cookie.getValue();
                break;
            }
        }
        
        Cart cart = new Cart(txt);
        int cartSize = cart.cartSize(txt);
        
        if(cartSize == 0){
            request.getRequestDispatcher("/common/emptycart.jsp").forward(request, response);
            return;
        }

        request.setAttribute("listSize", daoProductSize.getProductSizes());
        request.setAttribute("cart", cart.getItems());
       
        
        request.getRequestDispatcher("/common/cart.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
