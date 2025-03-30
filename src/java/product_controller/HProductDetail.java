/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package product_controller;

import dal.ProductDAO;
import dal.ProductFeedbackDAO;
import dal.ProductImageDAO;
import dal.ProductSizeDAO;
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
import model.ProductFeedback;
import model.ProductImg;
import model.ProductSize;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="HProductDetail", urlPatterns={"/hproductdetail"})
public class HProductDetail extends HttpServlet {
   
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
        ProductImageDAO pidao = new ProductImageDAO();
        ProductFeedbackDAO pfdao = new ProductFeedbackDAO();
        ProductSizeDAO psdao = new ProductSizeDAO();
        
        String proid = request.getParameter("proid");
        Product product = pdao.getProductById(Integer.parseInt(proid));
        List<ProductImg> piList = pidao.getAllProductImgById(proid);
        List<ProductFeedback> alldpfList = pfdao.getAllFeetBackByProductId(proid);
        List<ProductSize> psList = psdao.getAllProductSizeById(proid);
        int dsize = psList.get(0).getSize_id();
        int quantity = psList.get(0).getQuantity();
        String dcontent = "pdescription";
        int dfeedbackpage = 0;
        List<ProductFeedback> pf2List = pfdao.get2FeetBackByProductId(proid, 0);
        
        
        session.setAttribute("dcontent", dcontent);
        session.setAttribute("dfeedbackpage", dfeedbackpage);
        session.setAttribute("dsize", dsize);
        session.setAttribute("dquantity", quantity);
        session.setAttribute("dproduct", product);
        session.setAttribute("dpiList", piList);
        session.setAttribute("pf2List", pf2List);
        session.setAttribute("alldpfList", alldpfList);
        session.setAttribute("dpsList", psList);
       
        
        session.setAttribute("mainpage", "shop");
        response.sendRedirect(request.getContextPath()+"/common/hproductdetail.jsp");
        
         
       
        //Product product1 = (Product)session.getAttribute("dproduct");
    } 
    
    
    public static List<ProductFeedback> select2ProductsFeedback( List<ProductFeedback> pList, int pageNum){
        List<ProductFeedback> top2List = new ArrayList<>();
        for(int i = pageNum*2;i<=pageNum*2+1;i++){
            if(i>=pList.size()) {
                break;
            } else {
                top2List.add(pList.get(i));
            }
        }
        
        return top2List;
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
