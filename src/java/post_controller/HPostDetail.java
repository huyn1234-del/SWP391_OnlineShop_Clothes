/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package post_controller;

import dal.PostCategoryDAO;
import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Post;
import model.PostCategory;
import model.PostFeedback;
import model.User;

/**
 *
 * @author Dell
 */
@WebServlet(name="HPostDetail", urlPatterns={"/hpostdetail"})
public class HPostDetail extends HttpServlet {
   
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
        PostCategoryDAO pcdao = new PostCategoryDAO();
        
        String bid = request.getParameter("bid");
        if(bid==null){
            Post ppostdetail = (Post)session.getAttribute("ppostdetail");
            bid = ppostdetail.getPost_id()+"";
        }
        Post p = pdao.getPostByID(bid);
        PostCategory pc = pdao.getPostCategoryByPostID(bid);
        User u = pdao.getUserByPostID(bid);
        List<PostCategory> pcList = pcdao.getAllPostCategory();
        List<Post> plist = pdao.get3PostByCategoryId(pc.getPost_category_id()+"", bid);
       
        Post afterPost = pdao.getPostAfter(u.getUser_id()+"", bid);
        Post beforePost = pdao.getPostBefore(u.getUser_id()+"", bid);
        
        
        session.setAttribute("afterPost", afterPost);
        session.setAttribute("beforePost", beforePost);
        session.setAttribute("relatedPostList", plist);
        session.setAttribute("pofpage", 0);
        session.setAttribute("postcategorylist", pcList);
        session.setAttribute("ppostdetail", p);
        session.setAttribute("ppostcategory", pc);
        session.setAttribute("ppostauthor", u);
        
        
        
        response.sendRedirect(request.getContextPath()+"/common/hblogdetail.jsp");
        
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
