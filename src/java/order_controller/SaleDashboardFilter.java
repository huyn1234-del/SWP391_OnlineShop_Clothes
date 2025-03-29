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
 import java.text.SimpleDateFormat;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.time.temporal.ChronoUnit;
 import java.util.List;
 import model.SaleChart;
 import model.User;
 
 
 @WebServlet(name="SaleDashboardFilter", urlPatterns={"/saledashboardfilter"})
 public class SaleDashboardFilter extends HttpServlet {
    
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
         SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
         String pobegin = request.getParameter("begin");
         String poend = request.getParameter("end");
         
         int sid = 0;
         if(session.getAttribute("sdsaler")!=null){
             sid = Integer.parseInt(session.getAttribute("sdsaler")+"");
         }
         String loi ="";
         if((pobegin.length()!=0 && poend.length()==0) || (pobegin.length()==0 && poend.length()!=0)){
             loi = "Hãy nhập đủ cả 2 ngày bắt đầu và kết thúc";
             session.setAttribute("sdloi", loi);
             
         } else if(pobegin.length()!=0 && poend.length()!=0){
             session.setAttribute("sadbegin", pobegin);
             session.setAttribute("sadend", poend);
             LocalDate beginDate = LocalDate.parse(pobegin,formatter);
             LocalDate endDate = LocalDate.parse(poend,formatter);
             
             long diff = ChronoUnit.DAYS.between(beginDate, endDate);
             if(diff<0){
                 loi = "Ngày bắt đầu phải trước ngày kết thúc";
                 session.setAttribute("sdloi", loi);
                 
             } else {
         List<SaleChart> sList = odao.getSucsessOnTotalOrder(sid, beginDate, diff);
         int totalOrder = odao.getTotalOrder(sid, beginDate, diff);
         List<SaleChart> orderByDayList = odao.getNumberOfOrderByDay(sid, beginDate, diff);
         List<SaleChart> revenueByDayList = odao.getTotalRevenueByDay(sid, beginDate, diff);
         List<SaleChart> revenueAccumulateByDayList = odao.getRevenueAccumulateByDay(sid, beginDate, diff);
 
         
         session.setAttribute("sotoChart", sList);
         session.setAttribute("orderByDayList", orderByDayList);
         session.setAttribute("revenueByDayList", revenueByDayList);
         session.setAttribute("revenueAccumulateByDayList", revenueAccumulateByDayList);
         session.setAttribute("ctotalOrder", totalOrder);
             }
 
         }
         
         
         if(loi.length()==0) session.setAttribute("sdloi", "");
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
 
 