
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
import model.Product;
import model.ProductSize;
import model.Size;
import model.ProductImg;
import dal.ProductImageDAO;
import dal.ProductSizeDAO;
import dal.SizeDAO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;

@WebServlet(name="UpdateProduct", urlPatterns={"/updateproduct"})
@MultipartConfig(maxFileSize = 16177215)
public class UpdateProduct extends HttpServlet {
   
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        SizeDAO sdao = new SizeDAO();
        List<Size> sizes = sdao.getAllSize();
        ProductSizeDAO psdao = new ProductSizeDAO();
        ProductImageDAO pidao = new ProductImageDAO();
        
        HttpSession session = request.getSession();
        Product product = (Product)session.getAttribute("product_detail");
        
        Part file = request.getPart("img");
        String img = "";
        if(file.getSubmittedFileName().length() > 0) {
            String fileName = file.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "product_img";
            file.write(uploadPath + File.separator + fileName);
            img = "product_img/" + fileName;
        } else img = product.getThumbnail();

        boolean is_active = false;
        
        String name = request.getParameter("productname");
        int price = Integer.parseInt(request.getParameter("price"));
        int total_quantity = 0;
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        int brand = Integer.parseInt(request.getParameter("brand"));
        int category = Integer.parseInt(request.getParameter("category"));
        
        for (Size size : sizes) {
            int quantity = Integer.parseInt(request.getParameter("size_"+ size.getSize_id()));
            total_quantity += quantity;
        }
        
        if(status == 1) {
            is_active = true;
        }
        
        ProductDAO pdao = new ProductDAO();
        pdao.updateProduct(new Product(product.getProduct_id(),name, price, total_quantity, description, img, is_active, product.getRated_star(), brand, category));
        
        for (Size size : sizes) {
            int quantity = Integer.parseInt(request.getParameter("size_"+ size.getSize_id()));
            int weight = Integer.parseInt(request.getParameter("weight_"+ size.getSize_id()));
            if(quantity == 0 && weight > 0) {
                weight = 0;
            }
            if(psdao.getProductSize(size.getSize_id(), product.getProduct_id()) == null) {
                psdao.addSizeProduct(new ProductSize(size.getSize_id(), product.getProduct_id(), quantity, weight));
            }else psdao.updateSizeProduct(size.getSize_id(), product.getProduct_id(), quantity, weight);
           
        }
        
        List<ProductImg> productImg = pidao.getAllProductImgById(product.getProduct_id()+"");
        pidao.updateProductImage(new ProductImg(productImg.get(0).getProduct_image_id(),product.getProduct_id(), img, productImg.get(0).getIs_active()));
        
        for (int i = 1; i <= 3; i++) {
            Part newFile = request.getPart("img_" + i);
            String img_url = "";
            if(newFile.getSubmittedFileName().length() > 0) {
                String newFileName = newFile.getSubmittedFileName();
                String newUploadPath = getServletContext().getRealPath("") + File.separator + "product_img";
                newFile.write(newUploadPath + File.separator + newFileName);
                img_url = "product_img/" + newFileName;
            } else img_url = productImg.get(i).getImage_url();
            pidao.updateProductImage(new ProductImg(productImg.get(i).getProduct_image_id(), product.getProduct_id(),   img_url, productImg.get(i).getIs_active()));
        }
        
        request.getRequestDispatcher("productpaging").forward(request, response);
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
