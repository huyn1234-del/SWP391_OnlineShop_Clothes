package dal;

import model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends DBContext {

    public int getTotalQuantity(int pid) {
        String sql = "select SUM(quantity) from Product_Size where product_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public boolean updateTotalQuantity(int pid) {

        int totalQuantity = getTotalQuantity(pid);

        if (totalQuantity == 0) {
            return false;
        }
        String sql = """
                     UPDATE [dbo].[Products]
                         SET 
                            [total_quantity] = ?
                       WHERE product_id = ?""";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, totalQuantity);
            ps.setInt(2, pid);

            int n = ps.executeUpdate();

            return n > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean updateProductRating(int pid, int rating) {
        String sql = """
                     UPDATE [dbo].[Products]
                        SET 
                           [rated_star] = ?
                      WHERE [product_id] = ?""";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, rating);
            ps.setInt(2, pid);

            int n = ps.executeUpdate();

            return n > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Product getProductById(int pid) {

        Product p = new Product();
        String sql = "select * from Products where product_id=" + pid;

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                p = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<Product> get9Product(int n) {
        List<Product> pList = new ArrayList<>();
        String sql = "select * from Products\n"
                + "order by product_id\n"
                + "offset " + n + " rows fetch next 9 rows only";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getAllProductByName(String name) {
        List<Product> pList = new ArrayList<>();
        String sql = "select * from Products\n"
                + "where product_name like '%" + name + "%'\n";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getAllProductFilter(String sql) {
        List<Product> pList = new ArrayList<>();

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getAllProduct() {
        List<Product> pList = new ArrayList<>();
        String sql = "select * from Products";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getAllProductWithQuantity() {
        List<Product> pList = new ArrayList<>();
        String sql = "select * from Products where total_quantity > 0";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getAllRelatedProduct(String id) {
        List<Product> pList = new ArrayList<>();
        String sql = "select top 3 * from Products\n"
                + "where product_category_id = (\n"
                + "select product_category_id from Products\n"
                + "where product_id = ?\n"
                + ") and product_id!=?;";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.setString(2, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getHotProduct() {
        List<Product> pList = new ArrayList<>();
        String sql = "select top 8 p.product_id, p.product_name, p.price, p.total_quantity, p.description, p.thumbnail, p.is_active, p.rated_star, p.brand_id, p.product_category_id, sum(od.TotalPrice) as TotalPrice\n"
                + "from Products as p, Order_Details as od, Orders as o\n"
                + "where p.product_id = od.product_id and od.order_id = o.order_id and p.total_quantity > 0\n"
                + "and o.payment_status_id = 2\n"
                + "group by p.product_id, p.product_name, p.price, p.total_quantity, p.description, p.thumbnail, p.is_active, p.rated_star, p.brand_id, p.product_category_id\n"
                + "order by sum(od.TotalPrice) desc, p.product_id";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getAllHotProduct(String s) {
        List<Product> pList = new ArrayList<>();
        String sql = "select p.product_id, p.product_name, p.price, p.total_quantity, p.description, p.thumbnail, p.is_active, p.rated_star, p.brand_id, p.product_category_id, sum(od.TotalPrice) as TotalPrice\n"
                + "from Products as p\n"
                + "left join Order_Details as od on p.product_id = od.product_id\n"
                + "left join Orders as o on od.order_id = o.order_id\n"
                + "and o.payment_status_id = 2\n"
                + "where p.product_id in (" + s + ")\n"
                + "group by p.product_id, p.product_name, p.price, p.total_quantity, p.description, p.thumbnail, p.is_active, p.rated_star, p.brand_id, p.product_category_id\n"
                + "order by sum(od.TotalPrice) desc, p.product_id";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getNewProduct() {
        List<Product> pList = new ArrayList<>();
        String sql = "select top 8 * from Products where total_quantity > 0\n"
                + "order by product_id desc";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getRatingProduct() {
        List<Product> pList = new ArrayList<>();
        String sql = "select * from Products where total_quantity > 0 \n"
                + "order by rated_star desc";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                pList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<Product> getProductPaging(int index) {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from Products\n"
                + " order by product_id\n"
                + " offset ? rows\n"
                + " fetch next 3 rows only";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, (index - 1) * 3);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addProduct(Product p) {
        String sql = "insert into Products \n"
                + "(product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id)\n"
                + "values \n"
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, p.getProduct_name());
            pre.setInt(2, p.getPrice());
            pre.setInt(3, p.getTotal_quantity());
            pre.setString(4, p.getDescription());
            pre.setString(5, p.getThumbnail());
            pre.setBoolean(6, p.isIs_active());
            pre.setInt(7, p.getRated_star());
            pre.setInt(8, p.getBrand_id());
            pre.setInt(9, p.getProduct_category_id());
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(Product p) {
        String sql = "update Products set \n"
                + "product_name = ?, \n"
                + "price = ?, \n"
                + "total_quantity = ?, \n"
                + "description = ?, \n"
                + "thumbnail = ?, \n"
                + "is_active = ?, \n"
                + "rated_star = ?, \n"
                + "brand_id = ?, \n"
                + "product_category_id = ? \n"
                + "where product_id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, p.getProduct_name());
            pre.setInt(2, p.getPrice());
            pre.setInt(3, p.getTotal_quantity());
            pre.setString(4, p.getDescription());
            pre.setString(5, p.getThumbnail());
            pre.setBoolean(6, p.isIs_active());
            pre.setInt(7, p.getRated_star());
            pre.setInt(8, p.getBrand_id());
            pre.setInt(9, p.getProduct_category_id());
            pre.setInt(10, p.getProduct_id());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Product> searchProduct(String s) {
        List<Product> list = new ArrayList<>();
        String sql = """
                     select * from Products 
                      where product_name like ? and total_quantity > 0""";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%" + s + "%");
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                Product product = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getHighestId() {
        Product p = new Product();
        String sql = "select top 1 * from Products \n "
                + "order by product_id desc";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int price = rs.getInt("price");
                int total_quantity = rs.getInt("total_quantity");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                boolean is_active = rs.getBoolean("is_active");
                int rated_star = rs.getInt("rated_star");
                int brand_id = rs.getInt("brand_id");
                int product_category_id = rs.getInt("product_category_id");
                p = new Product(product_id, product_name, price, total_quantity, description, thumbnail, is_active, rated_star, brand_id, product_category_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
