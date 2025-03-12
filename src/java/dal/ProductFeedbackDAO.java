
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MKTChart;
import model.Product;
import model.ProductFeedback;
import model.SaleChart;
import model.User;


public class ProductFeedbackDAO extends DBContext {
    
    public int getAverageRatingOfProduct(int pid){
        
        String sql = """
                     select AVG(rating)
                      from Feedbacks
                      where product_id = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);

           ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductFeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean updateFeedback(ProductFeedback pf) {
        String sql = """
                     UPDATE [dbo].[Feedbacks]
                        SET 
                           [review] = ?
                           ,[thumbnail] = ?
                           ,[rating] = ?
                           ,[modified_at] = GETDATE()
                      WHERE feedback_id = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pf.getReview());
            ps.setString(2, pf.getThumbnail());
            ps.setInt(3, pf.getRating());
            ps.setInt(4, pf.getFeedback_id());

            int n = ps.executeUpdate();

            return n > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProductFeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertFeedback(ProductFeedback pf) {
        String sql = """
                     INSERT INTO [dbo].[Feedbacks]
                                ([customer_id]
                                ,[order_id]
                                ,[product_id]
                                ,[review]
                                ,[thumbnail]
                                ,[rating]
                                ,[is_active]
                                ,[create_at]
                                ,[modified_at])
                          VALUES
                                (?,?,?,?,?,?,?,GETDATE(),GETDATE())""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pf.getCustomer_id());
            ps.setInt(2, pf.getOrder_id());
            ps.setInt(3, pf.getProduct_id());
            ps.setString(4, pf.getReview());
            ps.setString(5, pf.getThumbnail());
            ps.setInt(6, pf.getRating());
            ps.setBoolean(7, true);

            int n = ps.executeUpdate();
            return n > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductFeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ProductFeedback getFeedbackByProductAndOrder(int oid, int pid) {
        String sql = "select * from Feedbacks where order_id = ? and product_id = ?";
        ProductDAO pdao = new ProductDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.setInt(2, pid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int feedback_id = rs.getInt("feedback_id");
                int customer_id = rs.getInt("customer_id");
                int order_id = oid;
                Product product = pdao.getProductById(pid);
                String review = rs.getString("review");
                String thumbnail = rs.getString("thumbnail");
                int rating = rs.getInt("rating");
                Date create_at = rs.getDate("create_at");
                Date update_at = rs.getDate("modified_at");

                return new ProductFeedback(feedback_id, customer_id, order_id, review, thumbnail, rating, create_at, update_at, product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductFeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<ProductFeedback> getAllFeetBackByProductId(String id) {
        List<ProductFeedback> pList = new ArrayList<>();
        String sql = "select f.*, u.username, u.profile_picture_url\n"
                + "from Feedbacks as f, Users as u\n"
                + "where f.customer_id = u.user_id and \n"
                + "product_id = ? and f.is_active = 1\n"
                + "and u.is_banned=0;";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int feedback_id = rs.getInt("feedback_id");
                int customer_id = rs.getInt("customer_id");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                String review = rs.getString("review");
                String thumbnail = rs.getString("thumbnail");
                int rating = rs.getInt("rating");
                int is_active = rs.getInt("is_active");
                Date create_at = rs.getDate("create_at");
                Date modified_at = rs.getDate("modified_at");
                String username = rs.getString("username");
                String customer_img = rs.getString("profile_picture_url");
                ProductFeedback pf = new ProductFeedback(feedback_id, customer_id, order_id, product_id, review, thumbnail, rating, is_active, create_at, modified_at, username, customer_img);
                pList.add(pf);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<ProductFeedback> get2FeetBackByProductId(String id, int n) {
        List<ProductFeedback> pList = new ArrayList<>();
        String sql = "select f.* ,u.username, u.profile_picture_url\n"
                + "from Feedbacks as f, Users as u\n"
                + "where f.customer_id = u.user_id and \n"
                + "product_id = ? and f.is_active = 1\n"
                + "and u.is_banned=0\n"
                + "order by feedback_id\n"
                + "offset ? rows fetch next 2 rows only";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.setInt(2, n);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int feedback_id = rs.getInt("feedback_id");
                int customer_id = rs.getInt("customer_id");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                String review = rs.getString("review");
                String thumbnail = rs.getString("thumbnail");
                int rating = rs.getInt("rating");
                int is_active = rs.getInt("is_active");
                Date create_at = rs.getDate("create_at");
                Date modified_at = rs.getDate("modified_at");
                String username = rs.getString("username");
                String customer_img = rs.getString("profile_picture_url");
                ProductFeedback pf = new ProductFeedback(feedback_id, customer_id, order_id, product_id, review, thumbnail, rating, is_active, create_at, modified_at, username, customer_img);
                pList.add(pf);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<ProductFeedback> getFeedbackByProduct() {
        List<ProductFeedback> pList = new ArrayList<>();
        String sql = "select * from Feedbacks   \n"
                + "   order by product_id\n";

        ProductDAO pdao = new ProductDAO();
        UserDAO udao = new UserDAO();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int feedback_id = rs.getInt("feedback_id");
                int customer_id = rs.getInt("customer_id");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                String review = showReview(rs.getString("review"));
                String thumnail = rs.getString("thumbnail");
                int rating = rs.getInt("rating");
                int is_active = rs.getInt("is_active");
                Date create_at = rs.getDate("create_at");
                Date modified_at = rs.getDate("modified_at");
                Product product = pdao.getProductById(product_id);
                User user = udao.getUserById(customer_id);
                ProductFeedback pf = new ProductFeedback(feedback_id, customer_id, order_id, review, thumnail, rating, is_active, create_at, create_at, product, user);
                pList.add(pf);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<ProductFeedback> SortProductFeedBackByUser() {
        List<ProductFeedback> pList = new ArrayList<>();
        String sql = "select * from Feedbacks   \n"
                + "   order by customer_id\n"
                + " "
                + "  ";
        ProductDAO pdao = new ProductDAO();
        UserDAO udao = new UserDAO();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int feedback_id = rs.getInt("feedback_id");
                int customer_id = rs.getInt("customer_id");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                String review = showReview(rs.getString("review"));
                String thumnail = rs.getString("thumbnail");
                int rating = rs.getInt("rating");
                int is_active = rs.getInt("is_active");
                Date create_at = rs.getDate("create_at");
                Date modified_at = rs.getDate("modified_at");
                Product product = pdao.getProductById(product_id);
                User user = udao.getUserById(customer_id);
                ProductFeedback pf = new ProductFeedback(feedback_id, customer_id, order_id, review, thumnail, rating, is_active, create_at, create_at, product, user);
                pList.add(pf);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public List<ProductFeedback> getAllProductFeedBack() {
        List<ProductFeedback> pList = new ArrayList<>();
        String sql = "select * from Feedbacks   \n";

        ProductDAO pdao = new ProductDAO();
        UserDAO udao = new UserDAO();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int feedback_id = rs.getInt("feedback_id");
                int customer_id = rs.getInt("customer_id");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                String review = showReview(rs.getString("review"));
                String thumnail = rs.getString("thumbnail");
                int rating = rs.getInt("rating");
                int is_active = rs.getInt("is_active");
                Date create_at = rs.getDate("create_at");
                Date modified_at = rs.getDate("modified_at");
                Product product = pdao.getProductById(product_id);
                User user = udao.getUserById(customer_id);
                ProductFeedback pf = new ProductFeedback(feedback_id, customer_id, order_id, review, thumnail, rating, is_active, create_at, create_at, product, user);
                pList.add(pf);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
        public ProductFeedback getProductFeedBackById(int id) {
            ProductFeedback pf=null;
        String sql = "select * from Feedbacks where feedback_id=?  \n";

        ProductDAO pdao = new ProductDAO();
        UserDAO udao = new UserDAO();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
              pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
           
            while (rs.next()) {
                int feedback_id = rs.getInt("feedback_id");
                int customer_id = rs.getInt("customer_id");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                String review = rs.getString("review");
                String thumnail = rs.getString("thumbnail");
                int rating = rs.getInt("rating");
                int is_active = rs.getInt("is_active");
                Date create_at = rs.getDate("create_at");
                Date modified_at = rs.getDate("modified_at");
                Product product = pdao.getProductById(product_id);
                User user = udao.getUserById(customer_id);
                 pf = new ProductFeedback(feedback_id, customer_id, order_id, review, thumnail, rating, is_active, create_at, create_at, product, user);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pf;
    }
    
    

    public List<ProductFeedback> sortProductFeedBack(String filter) {
        List<ProductFeedback> pList = new ArrayList<>();
        String sql = "select * from Feedbacks\n "
                + "order by " + filter;

        ProductDAO pdao = new ProductDAO();
        UserDAO udao = new UserDAO();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int feedback_id = rs.getInt("feedback_id");
                int customer_id = rs.getInt("customer_id");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                String review = showReview(rs.getString("review"));
                String thumnail = rs.getString("thumbnail");
                int rating = rs.getInt("rating");
                int is_active = rs.getInt("is_active");
                Date create_at = rs.getDate("create_at");
                Date modified_at = rs.getDate("modified_at");
                Product product = pdao.getProductById(product_id);
                User user = udao.getUserById(customer_id);
                ProductFeedback pf = new ProductFeedback(feedback_id, customer_id, order_id, review, thumnail, rating, is_active, create_at, create_at, product, user);
                pList.add(pf);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    public List<MKTChart> getAvgRatingByDay(LocalDate startDate, LocalDate endDate) {
        List<MKTChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select AVG(rating) as totalfeedback \n" +
                                  "from Feedbacks\n"
                +          "where create_at <= ? ";

          int daybetween= (int)ChronoUnit.DAYS.between(startDate, endDate);


        for (int i = 0; i <= daybetween; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
                pre.setString(1, date + "");

                ResultSet rs = pre.executeQuery();
                while (rs.next()) {

                    String fdate = dtf.format(date);
                    double value = rs.getDouble("totalfeedback");
                    MKTChart mktchart=new MKTChart(fdate, value);
                    sList.add(mktchart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return sList;
    }
    
    
     public List<SaleChart> getNewFeedBackEachDay(LocalDate startDate, LocalDate endDate) {
        List<SaleChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select COUNT(feedback_id) as totalfeedback\n" +
                                  "from Feedbacks\n"
                + "where YEAR(create_at)=? AND MONTH(create_at)=? AND DAY(create_at)=?  ";

          int daybetween= (int)ChronoUnit.DAYS.between(startDate, endDate);


        for (int i = 0; i <= daybetween; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
                pre.setString(1, date.getYear()+  "");
         pre.setString(2, date.getMonthValue()+  "");
             pre.setString(3, date.getDayOfMonth() +  "");
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {

                    String fdate = dtf.format(date);
                    int value = rs.getInt("totalfeedback");
                    SaleChart saleChart = new SaleChart(fdate, value);
                    sList.add(saleChart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return sList;
    }
    
    
       public void updateFeedbackStatus(ProductFeedback pf) {
        String sql = """
                     UPDATE [dbo].[Feedbacks]
                        SET 
                          is_active=?
                      WHERE feedback_id = ? """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pf.getIs_active());
            ps.setInt(2, pf.getFeedback_id());
           ps.executeUpdate();        

        } catch (SQLException ex) {
            Logger.getLogger(ProductFeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
      

 
    public String showReview(String review) {
        if (review.length() < 15) {
            return review;
        } else {
            return review.substring(0, 15) + "...";
        }
    }

    public int getTotalPage(int p, int n) {
        if (p % n == 0) {
            return p / n;
        } else {
            return p / n + 1;
        }
    }

}
