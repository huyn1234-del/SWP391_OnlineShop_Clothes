
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductSize;


public class ProductSizeDAO extends DBContext {

    public List<ProductSize> getProductSizes() {
        List<ProductSize> list = new ArrayList<>();
        String sql = "select distinct ps.size_id,ps.product_id,s.size_name,ps.quantity\n"
                + "from Product_Size ps join Sizes s on ps.size_id = s.size_id \n"
                + "group by ps.size_id,ps.product_id,s.size_name,ps.quantity";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int size_id = rs.getInt("size_id");
                int product_id = rs.getInt("product_id");
                String size_name = rs.getString("size_name");
                int quantity = rs.getInt("quantity");

                list.add(new ProductSize(size_id, product_id, quantity, size_name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ProductSize> getAll() {
        List<ProductSize> list = new ArrayList<>();
        String sql = "select distinct ps.size_id,ps.product_id,s.size_name\n"
                + "from Product_Size ps join Sizes s on ps.size_id = s.size_id \n"
                + "group by ps.size_id,ps.product_id,s.size_name";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int size_id = rs.getInt("size_id");
                int product_id = rs.getInt("product_id");
                String size_name = rs.getString("size_name");
                list.add(new ProductSize(size_id, product_id, size_name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ProductSize> getAllProductSizeById(String id) {
        List<ProductSize> pList = new ArrayList<>();
        String sql = "select distinct ps.*, s.size_name \n"
                + "from Product_Size as ps, Sizes as s\n"
                + "where ps.size_id = s.size_id and quantity>0 and product_id = " + id;

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int size_id = rs.getInt("size_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                String size_name = rs.getString("size_name");
                ProductSize ps = new ProductSize(size_id, product_id, quantity, size_name);
                pList.add(ps);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public void addSizeProduct(ProductSize p) {
        String sql = "insert into Product_Size \n"
                + "(size_id, product_id, quantity, weight) \n"
                + "values \n"
                + "(?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, p.getSize_id());
            pre.setInt(2, p.getProduct_id());
            pre.setInt(3, p.getQuantity());
            pre.setInt(4, p.getWeight());
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getQuantityOfEachSize(int sid, int pid) {
        String sql = "SELECT ps.quantity FROM Product_Size AS ps, Sizes AS s "
                + "WHERE ps.size_id = s.size_id AND ps.size_id = ? AND product_id = ?";
        int quantity = 0;  // Initialize to 0 in case there's no result.

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sid);
            pre.setInt(2, pid);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return quantity;
    }

    public int getWeightOfEachSize(int sid, int pid) {
        String sql = "SELECT ps.weight FROM Product_Size AS ps, Sizes AS s "
                + "WHERE ps.size_id = s.size_id AND ps.size_id = ? AND product_id = ?";
        int weight = 0;  // Initialize to 0 in case there's no result.

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sid);
            pre.setInt(2, pid);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                weight = rs.getInt("weight");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return weight;
    }

    public void updateQuantityOfEachSize(int size, int pid, int quantity) {
        String sql = """
                     update Product_Size set 
                     quantity = ?
                     where size_id = ? and product_id = ?""";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, size);
            pre.setInt(3, pid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateSizeProduct(int size, int pid, int quantity) {
        String sql = """
                     update Product_Size 
                     set 
                     quantity = ?
                     where size_id = ? and product_id = ?""";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, size);
            pre.setInt(3, pid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSizeProduct(int size, int pid, int quantity, int weight) {
        String sql = "update Product_Size set \n"
                + "quantity = ?, weight = ?\n"
                + "where size_id = ? and product_id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, weight);
            pre.setInt(3, size);
            pre.setInt(4, pid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ProductSize getProductSize(int size, int pid) {
        String sql = "select * from Product_Size \n"
                + "where size_id = ? and product_id = ?";
        ProductSize p = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, size);
            pre.setInt(2, pid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int size_id = rs.getInt("size_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                int weight = rs.getInt("weight");
                p = new ProductSize(size_id, product_id, quantity, weight);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
