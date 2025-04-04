
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
import java.util.List;
import model.Product;

@WebServlet(name="ProductPaging", urlPatterns={"/management/productpaging"})
public class ProductPaging extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        if (request.getParameter("p") != null) {
            int p = Integer.parseInt(request.getParameter("p"));
            ProductDAO pdao = new ProductDAO();
            List<Product> plist = pdao.getProductPaging(p);

            session.setAttribute("product_list", plist);
            session.setAttribute("cur_page", p);
            response.sendRedirect(request.getContextPath() + "/management/product-list.jsp");
        } else {
            int p = (int) session.getAttribute("cur_page");
            ProductDAO pdao = new ProductDAO();
            List<Product> plist = pdao.getProductPaging(p);
            
            session.setAttribute("product_list", plist);
            session.setAttribute("cur_page", p);
            response.sendRedirect(request.getContextPath() + "/management/product-list.jsp");
        }
        
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
