/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package post_controller;

import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import model.Post;
import model.User;

/**
 *
 * @author Dell
 */
@MultipartConfig
@WebServlet(name="AddPostMarketing", urlPatterns={"/addpostmarketing"})
public class AddPostMarketing extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PostDAO pdao = new PostDAO();
        User user = (User)session.getAttribute("account");
        int authorid = user.getUser_id();
        
        Part filePath = request.getPart("image");
        String filename = filePath.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("")+File.separator+"post_img";
        filePath.write(uploadPath+File.separator+filename);
        String img = "post_img/"+filename;
        
        String posttitle = request.getParameter("posttitle");
        String postcontent = request.getParameter("postcontent");
        String postcategory = request.getParameter("postcategory");
        int is_active = Integer.parseInt(request.getParameter("is_active"));
        int postCategoryId = Integer.parseInt(request.getParameter("postcategory"));
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        LocalDate td = LocalDate.parse(sdfDate.format(today),formatter);
        
        
        Post post = new Post(1, posttitle, postcontent, img, authorid, is_active, today, today, postCategoryId);
        pdao.AddNewPost(post);
        
        response.sendRedirect(request.getContextPath()+"/listpostmarketing");
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
