package product_controller;

import dal.PriceDAO;
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
import java.util.Comparator;
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
        
        String sql = "";
        String cid = request.getParameter("cid");
        String sid = request.getParameter("sid");
        String bid = request.getParameter("bid");
        
        if(sid==null && session.getAttribute("fsid")!=null) sid = session.getAttribute("fsid")+""; 
        if(cid==null && session.getAttribute("fcid")!=null) cid = session.getAttribute("fcid")+""; 
        if(bid==null && session.getAttribute("fbid")!=null) bid = session.getAttribute("fbid")+""; 
        
        if(sid!=null){
            session.setAttribute("fsid", sid);
            sql = "select distinct p.* \n" +
                "from Products as p, Product_Size as ps\n" +
                "where p.product_id=ps.product_id and ps.size_id = "+sid+" ";
            
            if(cid!=null){
                session.setAttribute("fcid", cid);
                sql+="and p.product_category_id="+cid+" ";
            }
            if(bid!=null){
                session.setAttribute("fbid", bid);
                 sql+="and brand_id = "+bid+" ";
            }
        } else if(cid!=null) {
            sql ="select p.* \n" +
                "from Products as p where ";
            if(cid!=null){
                session.setAttribute("fcid", cid);
                sql+="p.product_category_id="+cid+" ";
            }
            if(bid!=null){
                session.setAttribute("fbid", bid);
                 sql+="and brand_id = "+bid+" ";
            }
        } else if(bid!=null){
            sql ="select p.* \n" +
                "from Products as p where ";
            if(bid!=null){
                session.setAttribute("fbid", bid);
                 sql+="brand_id = "+bid+" ";
            }
            if(cid!=null){
                session.setAttribute("fcid", cid);
                sql+="and p.product_category_id="+cid+" ";
            }
        }
        
        String pid = request.getParameter("pid");
        if(pid==null && session.getAttribute("fpid")!=null) pid = session.getAttribute("fpid")+""; 
        
        if(request.getParameter("price1")!=null){
            int price1 = Integer.parseInt(request.getParameter("price1"));
            int price2 = Integer.parseInt(request.getParameter("price2"));
            pid = null;
            session.setAttribute("fpid", null);
            if(sql.length()>0){
                sql+="and price>"+price1+" and price<"+price2;
            } else {
                sql = "select * from Products\n" +
                        "where price>"+price1+" and price<"+price2;
            }
            session.setAttribute("price1", price1);
            session.setAttribute("price2", price2);
        }
        
        if(pid!=null){
            session.setAttribute("price1", null);
            session.setAttribute("price2", null);
            session.setAttribute("fpid", pid);
            if(pid.equals("max")){
                int maxPrice = Integer.parseInt(session.getAttribute("maxPrice")+"");
                if(sql.length()>0){
                    sql+="and price>"+maxPrice*1000;
                } else {
                    sql = "select * from Products\n" +
                            "where price>"+maxPrice*1000;
                }
            } else {
                Price p = prdao.getPriceById(pid);
                if(sql.length()>0){
                    sql+="and price>"+p.getFrom()*1000+" and price<"+p.getTo()*1000;
                } else {
                    sql = "select * from Products\n" +
                            "where price>"+p.getFrom()*1000+" and price<"+p.getTo()*1000;
                }
            }
        }
        
        List<Product> apList = pdao.getAllProductFilter(sql);
        apList = selectProductWithQuantity(apList);
        int cpage = 0;
        int totalProduct = apList.size();
        int npage = totalProduct/9 + 1;
        List<Product> p9List = select9Products(apList, cpage);
        
        session.setAttribute("apList", apList);
        session.setAttribute("ppList", p9List);
        session.setAttribute("ppage", npage);
        session.setAttribute("curpage", cpage);
        session.setAttribute("pname", "");
        session.setAttribute("totalProduct", totalProduct);
        
        session.setAttribute("sql", sql);
        
        response.sendRedirect(request.getContextPath()+"/common/product.jsp");
        
    } 
    
    public static List<Product> selectProductWithQuantity( List<Product> pList){
        List<Product> productList = new ArrayList<>();
        for (Product product : pList) {
            if(product.getTotal_quantity()>0){
                productList.add(product);
            }
        }
        return productList;
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
