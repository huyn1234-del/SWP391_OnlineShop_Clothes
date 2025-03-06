/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProductFeedback;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends DBContext {

    public List<ProductFeedback> getAllFeedback(String orderBy, String orderDir) {
        List<ProductFeedback> list = new ArrayList<>();
        String sql = "SELECT f.feedback_id, "
                + "       f.customer_id, "
                + "	   p.product_id, "
                + "	   CONCAT(u.first_name, ' ', u.last_name) AS customer_name, "
                + "	   p.product_name, "
                + "       f.review, "
                + "       f.rating, "
                + "       f.is_active "
                + "FROM Feedbacks f "
                + "JOIN Users u ON f.customer_id = u.user_id "
                + "JOIN Products p ON f.product_id = p.product_id ";

        // Xử lý sắp xếp
        if (orderBy != null && orderDir != null) {
            sql += " ORDER BY " + orderBy + " " + orderDir;
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductFeedback feedback = new ProductFeedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        rs.getString("customer_name"),
                        rs.getString("product_name"),
                        rs.getString("review"),
                        rs.getInt("rating"),
                        rs.getInt("is_active"));
                list.add(feedback);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<ProductFeedback> getFeedback(String name) {
        List<ProductFeedback> list = new ArrayList<>();
        String sql = "SELECT f.feedback_id, f.customer_id, p.product_id, "
                + "CONCAT(u.first_name, ' ', u.last_name) AS customer_name, "
                + "p.product_name, f.review, f.rating, f.is_active "
                + "FROM Feedbacks f "
                + "JOIN Users u ON f.customer_id = u.user_id "
                + "JOIN Products p ON f.product_id = p.product_id "
                + "WHERE CONCAT(u.first_name, ' ', u.last_name) LIKE ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + name + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductFeedback feedback = new ProductFeedback(
                            rs.getInt("feedback_id"),
                            rs.getInt("customer_id"),
                            rs.getInt("product_id"),
                            rs.getString("customer_name"),
                            rs.getString("product_name"),
                            rs.getString("review"),
                            rs.getInt("rating"),
                            rs.getInt("is_active")
                    );
                    list.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Thay vì `System.out.println(e)`, để dễ debug hơn
        }

        return list;
    }

    public ProductFeedback InfoFeedback(int id) {
        String sql = "SELECT f.feedback_id, "
                + "       f.customer_id, "
                + "	   p.product_id, "
                + "	   CONCAT(u.first_name, ' ', u.last_name) AS customer_name, "
                + "	   p.product_name, "
                + "       f.review, "
                + "       f.rating, "
                + "       f.is_active "
                + "FROM Feedbacks f "
                + "JOIN Users u ON f.customer_id = u.user_id "
                + "JOIN Products p ON f.product_id = p.product_id WHERE f.feedback_id = ? ";
        ProductFeedback feedback = null;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new ProductFeedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        rs.getString("customer_name"),
                        rs.getString("product_name"),
                        rs.getString("review"),
                        rs.getInt("rating"),
                        rs.getInt("is_active"));

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ProductFeedback getFeedbackById(int id) {
        ProductFeedback feedback = null;
        String sql = "SELECT f.feedback_id, "
                + "       CONCAT(u.first_name, ' ', u.last_name) AS customer_name, "
                + "       p.product_name, "
                + "       f.review, "
                + "       f.rating, "
                + "       f.is_active "
                + "FROM Feedbacks f "
                + "JOIN Users u ON f.customer_id = u.user_id "
                + "JOIN Products p ON f.product_id = p.product_id "
                + "WHERE f.feedback_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int feedbackId = rs.getInt("feedback_id");
                String customerName = rs.getString("customer_name");
                String productName = rs.getString("product_name");
                String review = rs.getString("review");
                int rating = rs.getInt("rating");
                int isActive = rs.getInt("is_active");

                // Khởi tạo đối tượng ProductFeedback với dữ liệu đúng
                feedback = new ProductFeedback(feedbackId, customerName, productName, review, rating, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public void updateFeedback(ProductFeedback f) {
        String sql = "UPDATE [dbo].[Feedbacks] SET "
                + " [review] = ?,"
                + " [rating] = ?,"
                + " [is_active] = ?"
                + " WHERE feedback_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, f.getReview());
            st.setInt(2, f.getRating());
            st.setInt(3, f.getIs_active());
            st.setInt(4, f.getFeedback_id());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
