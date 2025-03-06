package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.ProductCategory;


public class BrandDAO extends DBContext{
    public List<Brand> getAllBrand() {
        List<Brand> pList = new ArrayList<>();
        String sql = "select * from Brands";


        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int brand_id = rs.getInt("brand_id");
                String brand_name = rs.getString("brand_name");
                String description = rs.getString("description");
                String logo = rs.getString("logo");
                int is_active = rs.getInt("is_active");
                Brand b = new Brand(brand_id, brand_name, description, logo, is_active);
                pList.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    public Brand getBrandbyId(int brand_id){
        Brand b=null;
        String sql="select * from Brands\n" +
                    "where brand_id=?";
        try{
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setInt(1, brand_id);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                brand_id = rs.getInt("brand_id");
                String brand_name = rs.getString("brand_name");
                String description = rs.getString("description");
                String logo = rs.getString("logo");
                int is_active = rs.getInt("is_active");
                b = new Brand(brand_id, brand_name, description, logo, is_active);
                
            }
        }catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
    
}
