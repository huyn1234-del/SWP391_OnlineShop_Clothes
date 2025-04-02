/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package post_controller;

import dal.PostFeedbackDAO;
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
import java.nio.file.Paths;
import model.Post;
import model.PostFeedback;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="CustomerPostComment", urlPatterns={"/customerpostcomment"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CustomerPostComment extends HttpServlet {
   
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
    PostFeedbackDAO pfdao = new PostFeedbackDAO();  

    User user = (User) session.getAttribute("account");  
    if (user == null) {  
        response.sendRedirect(request.getContextPath() + "/login");  
        return;  
    }  

    String comment = request.getParameter("postcomment");  
   if (comment == null || comment.trim().isEmpty()) {  
    session.setAttribute("error", "Bình luận không được để trống hoặc chỉ chứa dấu cách.");  
    response.sendRedirect(request.getHeader("Referer"));  
    return;  
}

    if (comment.length() > 500) {  
        comment = comment.substring(0, 500);  
    }  

    int userid = user.getUser_id();  
    Post ppostdetail = (Post) session.getAttribute("ppostdetail");  
    int postid = ppostdetail.getPost_id();  

    // Xử lý file ảnh & video  
    Part imagePart = request.getPart("image");  
    Part videoPart = request.getPart("video");  

    String imageUrl = saveFile(imagePart, "product_img");  
    String videoUrl = saveFile(videoPart, "product_video");  

    // Thêm feedback vào database  
    PostFeedback pf = new PostFeedback(0, userid, postid, comment, 1, null, null, null, null, imageUrl, videoUrl, null, null, null);
    pfdao.AddCustomerPostFeedback(pf);  

    response.sendRedirect(request.getContextPath() + "/hpostdetail");  
}  

private String saveFile(Part filePart, String folder) throws IOException {  
    if (filePart == null || filePart.getSize() == 0) {  
        return null;  
    }  

    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();  
    String uploadPath = getServletContext().getRealPath("") + File.separator + folder;  

    File uploadDir = new File(uploadPath);  
    if (!uploadDir.exists()) {  
        uploadDir.mkdirs();  
    }  

    String filePath = uploadPath + File.separator + fileName;  
    filePart.write(filePath);  

    return folder + "/" + fileName;  
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
