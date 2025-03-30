package product_controller;

import dal.PriceDAO;
import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Price;
import model.Product;

@WebServlet(name="ProductFilter", urlPatterns={"/productfilter"})
public class ProductFilter extends HttpServlet {     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("sortValue", null);
        
        ProductDAO pdao = new ProductDAO();
        PriceDAO prdao = new PriceDAO();

        String sql = "SELECT * FROM Products WHERE 1=1"; // Mặc định lấy tất cả sản phẩm
        
        // Lọc theo danh mục, kích thước, thương hiệu
        String cid = request.getParameter("cid");
        String sid = request.getParameter("sid");
        String bid = request.getParameter("bid");

        if (sid == null && session.getAttribute("fsid") != null) sid = session.getAttribute("fsid") + "";  
        if (cid == null && session.getAttribute("fcid") != null) cid = session.getAttribute("fcid") + "";  
        if (bid == null && session.getAttribute("fbid") != null) bid = session.getAttribute("fbid") + "";  

        if (sid != null) {
            session.setAttribute("fsid", sid);
            sql += " AND product_id IN (SELECT product_id FROM Product_Size WHERE size_id = " + sid + ")";
        }
        if (cid != null) {
            session.setAttribute("fcid", cid);
            sql += " AND product_category_id = " + cid;
        }
        if (bid != null) {
            session.setAttribute("fbid", bid);
            sql += " AND brand_id = " + bid;
        }

        // Lọc theo đánh giá (rating star)
        String rate = request.getParameter("rate");
        if (rate == null && session.getAttribute("rate") != null) rate = session.getAttribute("rate") + "";
        if (rate != null) {
            session.setAttribute("rate", rate);
            sql += " AND rated_star >= " + rate; 
        }


        // Lấy danh sách sản phẩm sau khi lọc
        List<Product> apList = pdao.getAllProductFilter(sql);
        apList = selectProductWithQuantity(apList);
        
        int cpage = 0;
        int totalProduct = apList.size();
        int npage = (totalProduct / 9) + 1;
        List<Product> p9List = select9Products(apList, cpage);

        session.setAttribute("apList", apList);
        session.setAttribute("ppList", p9List);
        session.setAttribute("ppage", npage);
        session.setAttribute("curpage", cpage);
        session.setAttribute("totalProduct", totalProduct);
        session.setAttribute("sql", sql);

        response.sendRedirect(request.getContextPath() + "/common/product.jsp");
    }

    public static List<Product> selectProductWithQuantity(List<Product> pList) {
        List<Product> productList = new ArrayList<>();
        for (Product product : pList) {
            if (product.getTotal_quantity() > 0) {
                productList.add(product);
            }
        }
        return productList;
    }

    public static List<Product> select9Products(List<Product> pList, int pageNum) {
        List<Product> top9List = new ArrayList<>();
        for (int i = pageNum * 9; i < pageNum * 9 + 9 && i < pList.size(); i++) {
            top9List.add(pList.get(i));
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
}
