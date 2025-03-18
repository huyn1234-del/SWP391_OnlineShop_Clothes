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
import java.util.ArrayList;
import java.util.List;
import model.Post;
import model.PostCategory;
import model.ProductFeedback;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="HPostList", urlPatterns={"/hpostlist"})
public class HPostList extends HttpServlet {
   
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
        List<Post> pList = pdao.getAllPost();
        List<Post> top6post = select6Post(pList, 0);
        List<PostCategory> pcList = pcdao.getAllPostCategory();
        
        session.setAttribute("allpostlist", pList);
        session.setAttribute("top6post", top6post);
        session.setAttribute("postcategorylist", pcList);
        session.setAttribute("cpostpage", 0);
        
        
        //Reset
         Reset(session);
        
        response.sendRedirect(request.getContextPath()+"/common/post.jsp");
        
        
//        List<PostCategory> postcategorylist = (List<PostCategory>)session.getAttribute("postcategorylist");
//        for (PostCategory postCategory : postcategorylist) {
//            
//        }
        
    } 
    
    public static void Reset(HttpSession session){
        session.setAttribute("pobegin", "");
        session.setAttribute("poend", "");
        session.setAttribute("author", "");
        session.setAttribute("title", "");
        session.setAttribute("sortPostValue", "");
        session.setAttribute("mainpage", "blog");
        session.setAttribute("ploi", "");
    }
    
    public static List<Post> select6Post( List<Post> pList, int pageNum){
        List<Post> top6List = new ArrayList<>();
        for(int i = pageNum*6;i<=pageNum*6+5;i++){
            if(i>=pList.size()) {
                break;
            } else {
                top6List.add(pList.get(i));
            }
        }
        
        return top6List;
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
