/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package auth_controller;

import dal.DBContext;
import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Role;
import model.User;

/**
 *
 * @author ADMIN
 */
public class register extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        User account = (User) session.getAttribute("account");
        
        if(account == null){
            request.getRequestDispatcher("/account/register.jsp").forward(request, response);
            return;
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

        HttpSession session = request.getSession(true);

        UserDAO userDAO = new UserDAO();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userEmail = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");

        boolean checkExistUsername = userDAO.checkExistUsername(username);
        boolean checkExistEmail = userDAO.checkExistEmail(userEmail);

        if(username.isBlank()|| password.isBlank()){
            sendErrorMessage("Tài khoản và mật khẩu không thể trống!", request, response);
            return;
        }
        
        if (checkExistUsername) {
            sendErrorMessage("Tên tài khoản đã tồn tại!", request, response);
            return;
        }

        if (checkExistEmail) {
            sendErrorMessage("Địa chỉ email đã tồn tại!", request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            sendErrorMessage("Mật khẩu và xác nhận mật khẩu không khớp!", request, response);
            return;
        }
        if(dob.isEmpty()){
            dob = null;
        }
        if(firstname.isBlank()){
            firstname = null;
        }
        if(lastname.isBlank()){
            lastname = null;
        }

//        initialize user with customer role(id = 5)
        User newUser = new User(username, password, firstname, lastname, phone, userEmail, gender, dob, null, null, "profile_img/default.jpg", false, false, new Role(5));

//        insert to database
        Integer userId = userDAO.insertUser(newUser);

        if (userId == null) {
            sendErrorMessage("Đăng kí thất bại!", request, response);
            return;
        }

        //request.getRequestDispatcher("/verify?userId=" + userId).forward(request, response);
        response.sendRedirect(request.getContextPath() + "/verify?userId=" + userId);

    }

    private void sendErrorMessage(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("registerError", error);
        request.getRequestDispatcher("/account/register.jsp").forward(request, response);
    }

}
