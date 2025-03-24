/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cart_controller;

import dal.ProductDAO;
import dal.ProductSizeDAO;
import dal.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Cart;
import model.ProductSize;
import utils.Constants;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateCart", urlPatterns = {"/updateCart"})
public class UpdateCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        ProductSizeDAO psDAO = new ProductSizeDAO();
        ProductDAO productDAO = new ProductDAO();
        SizeDAO sizeDAO = new SizeDAO();

        try {

            int maxIndex = Integer.parseInt(request.getParameter("maxIndex"));
            String txt = "";
            for (int i = 0; i <= maxIndex; i++) {

                int pid = Integer.parseInt(request.getParameter("pid_" + i));
                int sid = Integer.parseInt(request.getParameter("sid_" + i));
                int quantity = Integer.parseInt(request.getParameter("quantity_" + i));

                if (txt.isBlank() || txt.isEmpty()) {
                    txt += pid + ":" + sid + ":" + quantity;
                } else {
                    txt += "#" + pid + ":" + sid + ":" + quantity;
                }
            }

            Cart c = new Cart(txt);
            String finalTxt = "";
            String cartQuantityError = "";
            for (String p : c.getFormatText().split("#")) {

                String[] s = p.split(":");

                int pid = Integer.parseInt(s[0]);
                int sid = Integer.parseInt(s[1]);
                int quantity = Integer.parseInt(s[2]);

                ProductSize productSize = psDAO.getProductSize(sid, pid);

                int stock = productSize.getQuantity();

                if (quantity > stock) {
                    quantity = stock;
                    cartQuantityError += "* " + productDAO.getProductById(pid).getProduct_name() + ", kích cỡ " + sizeDAO.getSizeById(sid).getSize_name() + " chỉ còn " + stock + " chiếc <br>";
                }

                if (finalTxt.isBlank() || finalTxt.isEmpty()) {
                    finalTxt += pid + ":" + sid + ":" + quantity;
                } else {
                    finalTxt += "#" + pid + ":" + sid + ":" + quantity;
                }
            }
            session.setAttribute("cartQuantityError", cartQuantityError);

            Cookie cart = new Cookie(Constants.COOKIE_CART, finalTxt);
            cart.setMaxAge(Constants.COOKIE_CART_MAXAGE);
            response.addCookie(cart);

            response.sendRedirect(request.getContextPath() + "/cart");

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/cart");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
