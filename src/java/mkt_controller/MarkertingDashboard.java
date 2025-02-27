/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package mkt_controller;

import dal.PostDAO;
import dal.PostFeedbackDAO;
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
import java.util.List;
import model.SaleChart;

@WebServlet(name="MarkertingDashboard", urlPatterns={"/markertingdashboard"})
public class MarkertingDashboard extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PostDAO pdao=new PostDAO();
        PostFeedbackDAO pfdao=new PostFeedbackDAO();
                HttpSession session = request.getSession();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
              String pobegin = "2024-09-20";
        LocalDate poDate = LocalDate.parse(pobegin, formatter);
            
        LocalDate endDate= LocalDate.now();
                
        List<SaleChart> postchart=pdao.getNumberPostByDay(poDate,endDate );
          List<SaleChart> posteachday=pdao.getPostEachDay(poDate,endDate );
          List<SaleChart> totalfeedback= pfdao.getNewFeedBackEachDay(poDate, endDate);
          List<SaleChart> newfeedback=pfdao.getNumberPostFeedBaclByDay(poDate, endDate);
          
       session.setAttribute("chart2", posteachday);
             session.setAttribute("chart1", postchart);
             session.setAttribute("chart3", totalfeedback);
             session.setAttribute("chart4", newfeedback);
             
             session.setAttribute("chart1name", "Tổng số bài post");
                 session.setAttribute("chart2name", "Số bài post theo ngày ");
                     session.setAttribute("chart3name", "Bình luận mới");
                         session.setAttribute("chart4name", "Tổng số bình luận");
             
        response.sendRedirect(request.getContextPath()+"/management/mkt_dashboard.jsp");
        
       
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
