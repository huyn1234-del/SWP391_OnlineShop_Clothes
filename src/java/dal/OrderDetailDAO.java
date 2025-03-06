
package dal;

import model.OrderDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailDAO extends DBContext {

    public static void main(String[] args) {
        OrderDetailDAO dao = new OrderDetailDAO();
        
        boolean check =dao.insertOrderDetail(new OrderDetail(10, 3, "", "", 1, 1, 356250, 356250));
        
        System.out.println(check);
    }
    
    public boolean insertOrderDetail(OrderDetail od) {
        String sql = """
               INSERT INTO [dbo].[Order_Details]
                          ([order_id]
                          ,[product_id]
                          ,[product_name]
                          ,[thumbnail]
                          ,[size_id]
                          ,[quantity]
                          ,[UnitPrice]
                          ,[TotalPrice])
                    VALUES
                          (?,?,?,?,?,?,?,?)""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getProductId());
            ps.setString(3, od.getProductName());
            ps.setString(4, od.getThumbnail());
            ps.setInt(5, od.getSizeId());
            ps.setInt(6, od.getQuantity());
            ps.setInt(7, od.getUnitPrice());
            ps.setInt(8, od.getTotalPrice());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<OrderDetail> getOrderDetailByOrderId(int oid) {
        String sql = """
                     select od.*,s.size_name 
                     from Order_Details od
                     join Sizes s on s.size_id = od.size_id
                     where order_id = ?""";
        List<OrderDetail> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, oid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt(1);
                int productId = rs.getInt(2);
                String productName = rs.getString(3);
                String thumbnail = rs.getString(4);
                int sizeId = rs.getInt(5);
                int quantity = rs.getInt(6);
                int unitPrice = rs.getInt(7);
                int totalPrice = rs.getInt(8);
                String sizeName = rs.getString(9);

                list.add(new OrderDetail(orderId, productId, productName, thumbnail, sizeId, quantity, unitPrice, totalPrice, sizeName));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
