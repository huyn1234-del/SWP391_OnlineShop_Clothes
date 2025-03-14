
package mkt_controller;

import dal.PostDAO;
import dal.ProductDAO;
import dal.SliderDAO;
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
import model.Product;
import model.Slider;

@WebServlet(name="MarketingHome", urlPatterns={"/marketinghome"})
public class MarketingHome extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
  HttpSession session = request.getSession();
        SliderDAO sld = new SliderDAO();
        ProductDAO pdao = new ProductDAO();
        PostDAO podao = new PostDAO();
        List<Slider> sList = sld.getAllSliders();  
        List<Product> pList = pdao.getHotProduct();
        List<Post> poList = podao.getNewPost();
        String tabfilter = "hot";
        
        session.setAttribute("mainpage", "home");
        session.setAttribute("hsList", sList);
        session.setAttribute("hpList", pList);
        session.setAttribute("poList", poList);
        session.setAttribute("tabfilter", tabfilter);
        response.sendRedirect(request.getContextPath()+"/common/home_marketing.jsp");
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
