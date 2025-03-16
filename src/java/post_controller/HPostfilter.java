/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package post_controller;

import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.Post;

/**
 *
 * @author Dell
 */
@WebServlet(name="HPostfilter", urlPatterns={"/hpostfilter"})
public class HPostfilter extends HttpServlet {
   
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
        
        String pobegin = request.getParameter("begin");
        String poend = request.getParameter("end");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String sortPostValue = request.getParameter("sortValue");
        
        if(pobegin.length()==0) session.setAttribute("pobegin", "");
        if(poend.length()==0) session.setAttribute("poend", "");
        if(author.length()==0) session.setAttribute("author", "");
        if(title.length()==0) session.setAttribute("title", "");
        
        
        String sql = "select p.*, (u.first_name+' '+u.last_name) as authorname  ,DATEDIFF(DAY, modified_at ,GETDATE()) from Posts as p, Users as u\n" +
                    "where p.author_id = u.user_id and u.is_banned=0 and p.is_active=1 ";
        
        
        String loi = "";
        
        //Khai bao date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        
        
        //TH1: chi nhap begim, ko nhap end:
        if((pobegin.length()!=0 && poend.length()==0) || (pobegin.length()==0 && poend.length()!=0)){
            loi = "Please input both From and To";
            session.setAttribute("ploi", loi);
            
        } else if(pobegin.length()!=0 && poend.length()!=0){
            session.setAttribute("pobegin", pobegin);
            session.setAttribute("poend", poend);
             LocalDate beginDate = LocalDate.parse(pobegin,formatter);
            LocalDate endDate = LocalDate.parse(poend,formatter);
            
            long diff = ChronoUnit.DAYS.between(beginDate, endDate);
            if(diff<0){
                loi = "To Date must be >= From Date";
                session.setAttribute("ploi", loi);
                
            } else {
                sql += "and modified_at >= '"+beginDate+"' and modified_at<='"+endDate+"' ";
            }

        }
        
        if(author.length()>0){
            session.setAttribute("author", author);
            sql+="and (u.first_name+' '+u.last_name) like '%"+author+"%' ";
        }
        if(title.length()>0){
            session.setAttribute("title", title);
            sql+="and title like '%"+title+"%' ";
        }
        if(sortPostValue.length()>0){
            session.setAttribute("sortPostValue", sortPostValue);
            if(sortPostValue.equals("new")){
                sql+="order by DATEDIFF(DAY, modified_at, GETDATE()) asc ";
            } else if(sortPostValue.equals("old")){
                 sql+="order by DATEDIFF(DAY, modified_at, GETDATE()) desc ";
            } else {
                sql += "and p.post_category_id = "+sortPostValue+" ";
            }
            
        } 
               
        List<Post> pList = pdao.getAllPostByFilter(sql);
        List<Post> top6post = select6Post(pList, 0);
        
        session.setAttribute("allpostlist", pList);
        session.setAttribute("top6post", top6post);
        session.setAttribute("cpostpage", 0);
        session.setAttribute("postsql", sql);
        if(loi.length()==0) session.setAttribute("ploi", "");
        response.sendRedirect(request.getContextPath()+"/common/post.jsp");
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
