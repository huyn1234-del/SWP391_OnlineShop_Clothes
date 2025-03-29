/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

 package order_controller;

 import dal.OrderDAO;
 import java.io.IOException;
 import java.io.PrintWriter;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.annotation.WebServlet;
 import jakarta.servlet.http.HttpServlet;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import jakarta.servlet.http.HttpSession;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.time.temporal.ChronoUnit;
 import java.util.List;
 import model.SaleChart;
 import model.User;
 
 
 @WebServlet(name="SaleDashboardFilterBySaler", urlPatterns={"/saledashboardfilterbysaler"})
 public class SaleDashboardFilterBySaler extends HttpServlet {
    
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
         OrderDAO odao = new OrderDAO();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         
         
         //7 ngay gan nhat
         LocalDate today = LocalDate.now();   
         LocalDate daybefore = today.minusDays(7);
         
         String pobeginDefault = "2024-10-01";
         LocalDate poDate = LocalDate.parse(pobeginDefault, formatter);
         
         int sid =Integer.parseInt(request.getParameter("sid"));
         session.setAttribute("sdsaler", sid);
         
         
         if(session.getAttribute("sadbegin")!=null){
             String pobegin = session.getAttribute("sadbegin")+"";
             String poend = session.getAttribute("sadend")+"";
             
             LocalDate beginDate = LocalDate.parse(pobegin,formatter);
             LocalDate endDate = LocalDate.parse(poend,formatter);
             long diff = ChronoUnit.DAYS.between(beginDate, endDate);
             
         
         List<SaleChart> sList = odao.getSucsessOnTotalOrder(sid, beginDate, diff);
         int totalOrder = odao.getTotalOrder(sid, beginDate,diff);
         List<SaleChart> orderByDayList = odao.getNumberOfOrderByDay(sid, beginDate, diff);
         List<SaleChart> revenueByDayList = odao.getTotalRevenueByDay(sid, beginDate, diff);
         List<SaleChart> revenueAccumulateByDayList = odao.getRevenueAccumulateByDay(sid, beginDate, diff);
         
         
         session.setAttribute("sotoChart", sList);
         session.setAttribute("orderByDayList", orderByDayList);
         session.setAttribute("revenueByDayList", revenueByDayList);
         session.setAttribute("revenueAccumulateByDayList", revenueAccumulateByDayList);
         session.setAttribute("ctotalOrder", totalOrder);
             
         } else{
             List<SaleChart> sList = odao.getSucsessOnTotalOrder(sid, poDate, 7);
         int totalOrder = odao.getTotalOrder(sid, poDate,7);
         List<SaleChart> orderByDayList = odao.getNumberOfOrderByDay(sid, poDate, 7);
         List<SaleChart> revenueByDayList = odao.getTotalRevenueByDay(sid, poDate, 7);
         List<SaleChart> revenueAccumulateByDayList = odao.getRevenueAccumulateByDay(sid, poDate, 7);
         
         
         session.setAttribute("sotoChart", sList);
         session.setAttribute("orderByDayList", orderByDayList);
         session.setAttribute("revenueByDayList", revenueByDayList);
         session.setAttribute("revenueAccumulateByDayList", revenueAccumulateByDayList);
         session.setAttribute("ctotalOrder", totalOrder);
         }
         
         
         response.sendRedirect(request.getContextPath()+"/management/saledashboard.jsp");
         
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
 
 