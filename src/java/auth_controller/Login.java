/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package auth_controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

import utils.Constants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
        
        Cookie[] cookies = request.getCookies();
        
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(Constants.COOKIE_REMEMBER)){
                request.setAttribute("username", cookie.getValue());
            }
                    
        }
        HttpSession session = request.getSession();
        
        User account = (User) session.getAttribute("account");
        
        if(account == null){
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        int roleId = account.getRole().getRole_id();
        
        switch (roleId) {
            case 1 -> response.sendRedirect(request.getContextPath()+"/admindashboard");
            case 2 -> response.sendRedirect(request.getContextPath()+"/salemanagerdashboard");
            case 3 -> response.sendRedirect(request.getContextPath()+"/orderlist");
            case 4 -> response.sendRedirect(request.getContextPath()+"/marketinghome");
//            default -> response.sendRedirect(request.getContextPath()+"/homeslider");

        }
        
        
        
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

        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        User user = userDAO.login(email, password);

        if (user == null) {
            sendErrorMessage("Tài khoản và mật khẩu không hợp lệ!", request, response);
            return;
        }

        if (user.isIs_banned()) {
            sendErrorMessage("Tài khoản của bạn đã bị khóa!", request, response);
            return;
        }

        if (!user.isIs_active()) {
            String activationLink = "<br><a href='" + request.getContextPath() + "/verify?userId=" + user.getUser_id() + "'>Ấn vào đây để kích hoạt tài khoản</a>";
            sendErrorMessage("Tài khoản chưa được kích hoạt!" + activationLink, request, response);
            return;
        }
        
        if("On".equals(rememberMe)){
            saveRememberMe(email, response);
        }
        
        session.setAttribute("account", user);

        int roleId = user.getRole().getRole_id();
        
        switch (roleId) {
            case 1 -> response.sendRedirect(request.getContextPath()+"/admindashboard");
            case 2 -> response.sendRedirect(request.getContextPath()+"/salemanagerdashboard");
            case 3 -> response.sendRedirect(request.getContextPath()+"/orderlist");
            case 4 -> response.sendRedirect(request.getContextPath()+"/marketinghome");
//            default -> response.sendRedirect(request.getContextPath()+"/homeslider");
            default -> response.sendRedirect("success.jsp");

        }
                
        
    }

    private void sendErrorMessage(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("loginError", error);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    private void saveRememberMe(String email, HttpServletResponse response){
        Cookie cookie = new Cookie(Constants.COOKIE_REMEMBER, email);
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);
    }
    

}
