package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductCategory;

public class ProductCategoryDAO extends DBContext{
    public List<ProductCategory> getAllProductCategory() {
        List<ProductCategory> pList = new ArrayList<>();
        String sql = "select * from Product_Categories";


        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_category_id = rs.getInt("product_category_id");
                String product_category_name = rs.getString("product_category_name");
                int is_active = rs.getInt("is_active");
                ProductCategory p = new ProductCategory(product_category_id, product_category_name, is_active);
                pList.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    public void addproductcate(ProductCategory p){
        try {
            String sql = "insert into Product_Categories( product_category_name, is_active) values   \n" +
                            "(? , ?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, p.getProduct_category_name());
            pre.setInt(2, p.getIs_active());
           pre.executeUpdate();

        } catch (SQLException ex) {
        }
    }
    public List<ProductCategory> searchCategory(String search){
        List<ProductCategory> pList = new ArrayList<>();
        String sql = "select * from Product_Categories\n" +
"   where product_category_name like ?";


        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%"+search+"%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_category_id = rs.getInt("product_category_id");
                String product_category_name = rs.getString("product_category_name");
                int is_active = rs.getInt("is_active");
                ProductCategory p =new ProductCategory(product_category_id, product_category_name, is_active);
                pList.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    public ProductCategory getProductCategories(int product_category_id){
       ProductCategory pc=null;
       String sql=" select * from Product_Categories\n" +
                  " where product_category_id=?";
       try{
           PreparedStatement pre= connection.prepareStatement(sql);
           pre.setInt(1, product_category_id);
           ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                product_category_id = rs.getInt("product_category_id");
                String product_category_name = rs.getString("product_category_name");
                int is_active = rs.getInt("is_active");
                pc =new ProductCategory(product_category_id, product_category_name, is_active);
                
            }
            
       }catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return pc;
   }
    public void updateProductCategory(ProductCategory pd){
        try {
            String sql = "Update Product_Categories\n" +
                        " set product_category_name=?,\n" +
                        " is_active=?\n" +
                        " where product_category_id=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, pd.getProduct_category_name());
            pre.setInt(2, pd.getIs_active());
            pre.setInt(3, pd.getProduct_category_id());
           pre.executeUpdate();

        } catch (SQLException ex) {

        }
    }
}
