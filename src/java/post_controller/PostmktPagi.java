/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package post_controller;

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

/**
 *
 * @author Dell
 */
@WebServlet(name="PostmktPagi", urlPatterns={"/postmktpagi"})
public class PostmktPagi extends HttpServlet {
   
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
        List<Post> allpostlist = (List<Post>)session.getAttribute("listpostmarketing");
        int cpage = Integer.parseInt(request.getParameter("cpage"));
        if(cpage<0) cpage=0;
        if(cpage>=(allpostlist.size()/3+1)) cpage = allpostlist.size()/3;
        List<Post> top3post = select3Post(allpostlist, cpage);
        
        
        
        session.setAttribute("top3postmarketing", top3post);
        session.setAttribute("cpostmkt", cpage);
        response.sendRedirect(request.getContextPath()+"/management/listpostmarketing.jsp");
        
    } 
    
    public static List<Post> select3Post( List<Post> pList, int pageNum){
        List<Post> top3List = new ArrayList<>();
        for(int i = pageNum*3;i<=pageNum*3+2;i++){
            if(i>=pList.size()) {
                break;
            } else {
                top3List.add(pList.get(i));
            }
        }
        
        return top3List;
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
