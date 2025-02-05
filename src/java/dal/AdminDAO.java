/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.FeedBack;
import model.Order;
import model.OrderDetail;
import model.OrderStatus;
import model.Product;

/**
 *
 * @author user
 */
public class AdminDAO {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Order> getAllOrder(){
        List<Order> list = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try {
            con = new DBContext().getConnection();// mo ket noi vs sqlsv
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getString("ordered_date"),
                        rs.getString("receive_date"),
                        rs.getString("receiver_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getInt("total_price"),
                        rs.getInt("shipping_fee"),
                        rs.getInt("voucher_id"),
                        rs.getInt("voucher_percent"),
                        rs.getInt("total_amount"),
                        rs.getInt("payment_method_id"),
                        rs.getInt("payment_status_id"),
                        rs.getInt("order_status_id"),
                        rs.getString("shipping_code"),
                        rs.getInt("salerId")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<FeedBack> getAllFeedBacks(){
        List<FeedBack> list = new ArrayList<>();
        String query = "select * from Feedbacks";
        try {
            con = new DBContext().getConnection();// mo ket noi vs sqlsv
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new FeedBack(
                        rs.getInt("feedback_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getString("review"),
                        rs.getString("thumbnail"),
                        rs.getInt("rating"),
                        rs.getBoolean("is_active"),
                        rs.getDate("create_at"),
                        rs.getDate("modified_at")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public OrderStatus getOrderStatusByID(String Id){
        String query = "select * from Order_Status where order_status_id = ?";
        try {
            con = new DBContext().getConnection();// mo ket noi vs sqlsv
            ps = con.prepareStatement(query);
            ps.setString(1, Id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                return (new OrderStatus(
                        rs.getInt("order_status_id"),
                        rs.getString("order_status_name"), 
                        rs.getString("description"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Product getProductByID(int Id){
        String query = "select * from Products where product_id = ?";
        try {
            con = new DBContext().getConnection();// mo ket noi vs sqlsv
            ps = con.prepareStatement(query);
            ps.setInt(1, Id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                return (new Product(rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("price"), 
                        rs.getInt("total_quantity"),
                        rs.getInt("discount"),
                        rs.getString("description"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("is_active"),
                        rs.getInt("rated_star"),
                        rs.getInt("brand_id"),
                        rs.getInt("product_category_id")
                ));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        String cId ="1";
        OrderStatus orderStatus = dao.getOrderStatusByID(cId);
        Product p = dao.getProductByID(1);
        System.out.println(p);
        List<FeedBack> list = dao.getAllFeedBacks();
        for (FeedBack o : list) {
            System.out.println(o);
        }
    }
    
}
