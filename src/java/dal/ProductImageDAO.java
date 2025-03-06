
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductImg;

public class ProductImageDAO extends DBContext{
    public List<ProductImg> getAllProductImgById(String id) {
        List<ProductImg> pList = new ArrayList<>();
        String sql = "select top 4 * from Product_Images where is_active = 1 and product_id = "+id;

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_image_id = rs.getInt("product_image_id");
                int product_id = rs.getInt("product_id");
                String image_url = rs.getString("image_url");
                int is_active = rs.getInt("is_active");
                ProductImg pi = new ProductImg(product_image_id, product_id, image_url, is_active);
                pList.add(pi);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
   public void addProductImage(ProductImg p) {
       String sql = "insert into Product_Images \n"
                + "(product_id, image_url, is_active) \n"
                + "values \n"
                + "(?, ?, 1)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, p.getProduct_id());
            pre.setString(2, p.getImage_url());
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void updateProductImage(ProductImg p) {
       String sql = "update Product_Images set\n"
               + "image_url = ?, "
               + "is_active = ? \n"
               + "where product_image_id = ? and product_id = ?";
       try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, p.getImage_url());
            pre.setInt(2, p.getIs_active());
            pre.setInt(3, p.getProduct_image_id());
            pre.setInt(4, p.getProduct_id());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
