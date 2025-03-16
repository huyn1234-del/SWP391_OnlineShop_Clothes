package product_controller;

import dal.ProductDAO;
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
import model.Product;

@WebServlet(name="Pagination", urlPatterns={"/pagination"})
public class Pagination extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ProductDAO pdao = new ProductDAO();
        
        List<Product> apList = (List<Product>)session.getAttribute("apList");
        
        int totalProduct = apList.size();
        int npage = totalProduct/9 + 1;
        int cpage = Integer.parseInt(request.getParameter("cpage"));
        List<Product> p9List = select9Products(apList, cpage);
        
        session.setAttribute("ppList", p9List);  
        session.setAttribute("curpage", cpage);
        session.setAttribute("ppage", npage);
        session.setAttribute("totalProduct", totalProduct);
        response.sendRedirect(request.getContextPath()+"/common/product.jsp");
    } 
    
    
     public static List<Product> select9Products( List<Product> pList, int pageNum){
        List<Product> top9List = new ArrayList<>();
        for(int i = pageNum*9;i<=pageNum*9+8;i++){
            if(i>=pList.size()) {
                break;
            } else {
                top9List.add(pList.get(i));
            }
        }
        
        return top9List;
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

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
