
package mkt_controller;

import dal.OrderDAO;
import dal.PostDAO;
import dal.PostFeedbackDAO;
import dal.ProductFeedbackDAO;
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
import model.MKTChart;
import model.SaleChart;


@WebServlet(name="MKTDashboardFilter", urlPatterns={"/mktdashboardfilter"})
public class MKTDashboardFilter extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String pobegin = request.getParameter("begin");
        String poend = request.getParameter("end");
        HttpSession session=request.getSession(true);
        PostDAO pdao=new PostDAO();
        OrderDAO odao=new OrderDAO();
        PostFeedbackDAO pfdao=new PostFeedbackDAO();
        ProductFeedbackDAO prfdao=new ProductFeedbackDAO();
         LocalDate beginDate;
         LocalDate endDate;
        String loi ="";
            String filter=request.getParameter("filter");
        if(pobegin.length()==0 || poend.length()==0){
            loi = "Please input both From and To";
            session.setAttribute("mkt_de", loi);
            
        } else if(pobegin.length()!=0 && poend.length()!=0){
            session.setAttribute("sadbegin", pobegin);
            session.setAttribute("sadend", poend);
             beginDate = LocalDate.parse(pobegin,formatter);
             endDate = LocalDate.parse(poend,formatter);
            
            long diff = ChronoUnit.DAYS.between(beginDate, endDate);
            if(diff<0){
                loi = "To Date must be >= From Date";
                session.setAttribute("mkt_de", loi);
            
            }
        List<SaleChart> chart1;
        List<SaleChart> chart2;
        List<SaleChart> chart3;
        List<SaleChart> chart4;
              
            
    
        
        if(filter.equals("post"))
        {
       chart1=pdao.getNumberPostByDay(beginDate,endDate );
           chart2=pdao.getPostEachDay(beginDate,endDate );
        chart3= pfdao.getNewFeedBackEachDay(beginDate, endDate);
       chart4=pfdao.getNumberPostFeedBaclByDay(beginDate, endDate);
          
       session.setAttribute("chart1", chart1);
             session.setAttribute("chart2", chart2);
             session.setAttribute("chart3", chart3);
             session.setAttribute("chart4", chart4);
        }
        else if(filter.equals("product"))
        {
            chart1=odao.getAmmountPerDay(beginDate, endDate);
                   session.setAttribute("chart1", chart1);
                   session.setAttribute("chart1name", "Sản phẩm đã bán");
                     chart2=odao.getTotalAmountByDay(beginDate, endDate);
                     session.setAttribute("chart2", chart2);
                     session.setAttribute("chart2name", "Tổng số sản phẩm bán được");
                     chart3=odao.getRevenueAccumulateByDay(0, beginDate, diff);
                      session.setAttribute("chart3", chart3);
                     session.setAttribute("chart3name", "Tổng doanh số");
                     chart4=odao.getTotalRevenueByDay(0, beginDate, diff);
                        session.setAttribute("chart4", chart4);
                     session.setAttribute("chart4name", "Doanh số theo ngày");
                     
                     
        }
        else if(filter.equals("customer"))
        {
            List<MKTChart> avgrating=prfdao.getAvgRatingByDay(beginDate, endDate);
             session.setAttribute("chart1", avgrating);
                   session.setAttribute("chart1name", "Đánh giá trung bình");
            chart2=prfdao.getNewFeedBackEachDay(beginDate, endDate);
             session.setAttribute("chart2", chart2);
                   session.setAttribute("chart2name", "Lượt đánh  giá mới");
            
        }
        
          session.setAttribute("filter", filter);
        
    }
      session.setAttribute("mkt_de", loi);
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
