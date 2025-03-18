/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package product_controller;

import dal.BrandDAO;
import dal.PriceDAO;
import dal.ProductCategoryDAO;
import dal.ProductDAO;
import dal.SizeDAO;
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
import model.Brand;
import model.Price;
import model.Product;
import model.ProductCategory;
import model.Size;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="HomeProductSearch", urlPatterns={"/homeproductsearch"})
public class HomeProductSearch extends HttpServlet {
   
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
        ProductDAO pdao = new ProductDAO();
        int cpage = 0;
        
        String name = request.getParameter("pname");
        
        if(name.length()==0) {
            response.sendRedirect(request.getContextPath()+"/homeproduct");
        } else {
        List<Product> apList = pdao.getAllProductByName(name);
        
        int totalProduct = apList.size();
        int npage = apList.size()/9 + 1;    
        List<Product> p9List = select9Products(apList, cpage);
        
        
        session.setAttribute("sql", null);
        session.setAttribute("fcid", null);
        session.setAttribute("fbid", null);
        session.setAttribute("fsid", null);
        session.setAttribute("fpid", null);
        session.setAttribute("apList", apList);  
        session.setAttribute("ppList", p9List);  
        session.setAttribute("pname", name);
        session.setAttribute("ppage", npage);
        session.setAttribute("curpage", cpage);
        session.setAttribute("totalProduct", totalProduct);
        
        
        //Set up for filter:
        ProductCategoryDAO pcdao = new ProductCategoryDAO();
        BrandDAO bdao = new BrandDAO();
        SizeDAO sdao = new SizeDAO();
        PriceDAO prdao = new PriceDAO();
        List<ProductCategory> pcList = pcdao.getAllProductCategory();
        List<Brand> bList = bdao.getAllBrand();
        List<Size> sList = sdao.getAllSize();
        List<Price> prList = prdao.getAllPrice();
        int max = getMaxPrice(prList);
        session.setAttribute("maxPrice", max);
        session.setAttribute("prList", prList);
        session.setAttribute("bList", bList);
        session.setAttribute("sList", sList);
        session.setAttribute("pcList", pcList);
        session.setAttribute("mainpage", "shop");
        response.sendRedirect(request.getContextPath()+"/common/product.jsp");
        }
        
        
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
    
   
    
    
    public static int getMaxPrice(List<Price> pList){
        int max = 0;
        for (Price price : pList) {
            if(price.getTo()>max) max = price.getTo();
        }
        
        return max;
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
