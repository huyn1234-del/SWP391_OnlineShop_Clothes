/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cart_controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utils.Constants;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RemoveOne", urlPatterns = {"/removeOne"})
public class RemoveOne extends HttpServlet {

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

        Cookie[] cookies = request.getCookies();
        String txt = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Constants.COOKIE_CART)) {
                txt = cookie.getValue();
                break;
            }
        }

        if (txt.isBlank() || txt.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        String pid = request.getParameter("pid");
        String sid = request.getParameter("sid");

        String[] products = txt.split("#");

        String newTxt = "";
        for (String p : products) {

            String[] s = p.split(":");

            if (!s[0].equals(pid) || !s[1].equals(sid)) {

                if (newTxt.isBlank() || newTxt.isEmpty()) {
                    newTxt += s[0] + ":" + s[1] + ":" + s[2];
                } else {
                    newTxt += "#" + s[0] + ":" + s[1] + ":" + s[2];
                }
            }
        }

        Cookie cart = new Cookie(Constants.COOKIE_CART, newTxt);
        cart.setMaxAge(Constants.COOKIE_CART_MAXAGE);
        response.addCookie(cart);
        response.sendRedirect(request.getContextPath() + "/cart");

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
