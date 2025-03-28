/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package auth_controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

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
        request.getRequestDispatcher("/account/changePassword.jsp").forward(request, response);
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

        User account = (User) session.getAttribute("account");
        
        User user = userDAO.getUserById(account.getUser_id());

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!currentPassword.equals(user.getPassword())) {
            request.setAttribute("error", "Sai mật khẩu hiện tại!");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }
        
        if (newPassword.isBlank()) {
            request.setAttribute("error", "Mật khẩu không thể để trống!");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }
        
        if (newPassword.equals(currentPassword)) {
            request.setAttribute("error", "Mật khẩu mới không thể trùng với mật khẩu hiện tại!");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }
        
        

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp!");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }

        boolean checkUpdatePassword = userDAO.updatePassword(user.getEmail(), newPassword);

        if (checkUpdatePassword) {
            request.setAttribute("success", "Thay đổi mật khẩu thành công.");
            request.getRequestDispatcher("/account/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Đã có lỗi xảy ra khi đổi mật khẩu. Vui lòng thử lại sau.");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
        }
    }

}
