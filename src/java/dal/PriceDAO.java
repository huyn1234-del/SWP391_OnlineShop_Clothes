package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Price;
import model.Product;

public class PriceDAO extends DBContext{
    public List<Price> getAllPrice () {
        List<Price> pList = new ArrayList<>();
        String sql = "select * from Prices\n" +
                        "order by [from]";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int price_id = rs.getInt("price_id");          
                int from = rs.getInt("from");
                int to = rs.getInt("to");
                int is_active = rs.getInt("is_active");
                Price p = new Price(price_id, from, to, is_active);
                pList.add(p);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    public Price getPriceById (String id) {
        
        String sql = "select * from Prices\n" +
                    "where price_id = "+id;
        Price p = new Price();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int price_id = rs.getInt("price_id");          
                int from = rs.getInt("from");
                int to = rs.getInt("to");
                int is_active = rs.getInt("is_active");
                p = new Price(price_id, from, to, is_active);
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
