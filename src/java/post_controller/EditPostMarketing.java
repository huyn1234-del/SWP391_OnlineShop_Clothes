
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
import model.Post;


@MultipartConfig
@WebServlet(name="EditPostMarketing", urlPatterns={"/management/editpostmarketing"})
public class EditPostMarketing extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PostDAO pdao = new PostDAO();
        Post editpostmkt = (Post)session.getAttribute("editpostmkt");
        String img ="";
        Part filePath = request.getPart("image");
        if(filePath.getSubmittedFileName().length()>0){ 
            String filename = filePath.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("")+File.separator+"post_img";
            filePath.write(uploadPath+File.separator+filename);
            img = "post_img/"+filename;
        }
         else img = editpostmkt.getThumbnail();
        
        String posttitle = request.getParameter("posttitle");
        String postcontent = request.getParameter("postcontent");
        String postcategory = request.getParameter("postcategory");
        int is_active = Integer.parseInt(request.getParameter("is_active"));
        int postCategoryId = Integer.parseInt(request.getParameter("postcategory"));
         
        Post newPost = new Post(editpostmkt.getPost_id(), posttitle, postcontent, img, editpostmkt.getAuthor_id(), is_active, editpostmkt.getCreated_at(), null, postCategoryId);
        pdao.EditPost(newPost);
        response.sendRedirect(request.getContextPath()+"/management/listpostmarketing");
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        PostDAO pdao = new PostDAO();
        
        String pid = request.getParameter("pid");
        Post post = pdao.getPostByID(pid);
        
        session.setAttribute("editpostmkt", post);
        
        response.sendRedirect(request.getContextPath()+"/management/editpostmarketing.jsp");
        
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
