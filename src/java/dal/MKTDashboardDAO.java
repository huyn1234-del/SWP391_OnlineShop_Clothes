/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.Post;

/**
 *
 * @author Admin
 */
public class MKTDashboardDAO extends DBContext {

    public int getCountPost() {
        String sql = "SELECT COUNT(*) FROM Posts";
        int count = 0;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();  

            if (rs.next()) {  // Di chuyển con trỏ đến dòng đầu tiên
                count = rs.getInt(1);  // Lấy giá trị của COUNT(*)
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return count;  // Trả về kết quả đếm
    }
    
    public int getCountProduct() {
        String sql = "SELECT COUNT(*) FROM products";
        int count = 0;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();  

            if (rs.next()) {  // Di chuyển con trỏ đến dòng đầu tiên
                count = rs.getInt(1);  // Lấy giá trị của COUNT(*)
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return count;  // Trả về kết quả đếm
    }
    
    public int getCountCustomer() {
        String sql = "SELECT COUNT(*) FROM Users";
        int count = 0;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();  

            if (rs.next()) {  // Di chuyển con trỏ đến dòng đầu tiên
                count = rs.getInt(1);  // Lấy giá trị của COUNT(*)
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;  // Trả về kết quả đếm
    }
    
    public int getCountFeedback() {
        String sql = "SELECT COUNT(*) FROM Feedbacks";
        int count = 0;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();  

            if (rs.next()) {  // Di chuyển con trỏ đến dòng đầu tiên
                count = rs.getInt(1);  // Lấy giá trị của COUNT(*)
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return count;  // Trả về kết quả đếm
    }

}
