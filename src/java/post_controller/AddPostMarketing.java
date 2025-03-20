
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


@MultipartConfig
@WebServlet(name="AddPostMarketing", urlPatterns={"/management/addpostmarketing"})
public class AddPostMarketing extends HttpServlet {
   
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
        
        response.sendRedirect(request.getContextPath()+"/management/listpostmarketing");
        
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
