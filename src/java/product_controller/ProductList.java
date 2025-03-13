

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

@WebServlet(name="ProductList", urlPatterns={"/productlist"})
public class ProductList extends HttpServlet {
   

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    ProductDAO pdao = new ProductDAO();
    List<Product> list = pdao.getAllProduct(); // Lấy tất cả sản phẩm
    List<Product> plist = pdao.getProductPaging(1); // Lấy sản phẩm của trang 1
    HttpSession session = request.getSession();
    
    session.setAttribute("product_list", plist); // Lưu danh sách sản phẩm trang hiện tại vào session
    session.setAttribute("cur_page", 1); // Lưu số trang hiện tại vào session
    session.setAttribute("num_page", getNumberOfPage(list.size(), 3)); // Lưu tổng số trang vào session
    response.sendRedirect(request.getContextPath() + "/management/product-list.jsp"); // Chuyển hướng đến trang product-list.jsp
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
    
     public int getNumberOfPage(int length, int n)
    {
        if(length%n == 0) return length/n;
        else return length/n + 1;
    }
}
