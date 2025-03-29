

package order_controller;

import dal.OrderDAO;
import dal.UserDAO;
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

@WebServlet(name="SaleDashboard", urlPatterns={"/saledashboard"})
public class SaleDashboard extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.now();   
        LocalDate daybefore = today.minusDays(7);
        
        String pobegin = "2025-01-01";
        String poend = "2025-01-07";
        LocalDate poDate = LocalDate.parse(pobegin, formatter);
        
//         LocalDate beginDate = LocalDate.parse(pobegin,formatter);
//            LocalDate endDate = LocalDate.parse(poend,formatter);
//            
//            long diff = ChronoUnit.DAYS.between(beginDate, endDate);

        OrderDAO odao = new OrderDAO();
        UserDAO udao = new UserDAO();
        List<SaleChart> sList = odao.getSucsessOnTotalOrder(0, daybefore, 7);
        int totalOrder = odao.getTotalOrder(0, daybefore, 7);
        List<SaleChart> orderByDayList = odao.getNumberOfOrderByDay(0, daybefore, 7);
        List<SaleChart> revenueByDayList = odao.getTotalRevenueByDay(0, daybefore, 7);
        List<SaleChart> revenueAccumulateByDayList = odao.getRevenueAccumulateByDay(0, daybefore, 7);
        List<User> salerList = udao.getAllSaler();
        
        session.setAttribute("sotoChart", sList);
        session.setAttribute("orderByDayList", orderByDayList);
        session.setAttribute("revenueByDayList", revenueByDayList);
        session.setAttribute("revenueAccumulateByDayList", revenueAccumulateByDayList);
        session.setAttribute("dsalerList", salerList);
        
        session.setAttribute("ctotalOrder", totalOrder);
        Reset(session);
        
       
        session.setAttribute("sadbegin", daybefore);
        session.setAttribute("sadend", today);
        response.sendRedirect(request.getContextPath()+"/management/saledashboard.jsp");
    } 
    
    
    
     public static void Reset(HttpSession session){
        session.setAttribute("sdsaler", null);
        session.setAttribute("sadbegin", null);
        session.setAttribute("sadend", null);
        session.setAttribute("sdloi", null);
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

