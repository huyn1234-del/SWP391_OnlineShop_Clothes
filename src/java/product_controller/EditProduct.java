
package product_controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;

@WebServlet(name="EditProduct", urlPatterns={"/management/editproduct"})
@MultipartConfig(maxFileSize = 16177215)
public class EditProduct extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        ProductDAO pdao = new ProductDAO();
        
        if (request.getParameter("button")!= null) {
            String button = request.getParameter("button");
            int pid = Integer.parseInt(request.getParameter("pid"));
            if (button.equals("hide")) {
                Product p = pdao.getProductById(pid);
                p.setIs_active(false);
                pdao.updateProduct(p);
                request.getRequestDispatcher("/management/productpaging").forward(request, response);
            } else if (button.equals("show")) {
                Product p = pdao.getProductById(pid);
                p.setIs_active(true);
                pdao.updateProduct(p);
                request.getRequestDispatcher("/management/productpaging").forward(request, response);
            } else {
                Product p = pdao.getProductById(pid);
                session.setAttribute("product_detail", p);
                response.sendRedirect(request.getContextPath() + "/management/edit-product.jsp");
            }
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
