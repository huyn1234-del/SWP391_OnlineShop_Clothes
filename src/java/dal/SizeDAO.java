
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Size;

public class SizeDAO extends DBContext{
     public List<Size> getAllSize() {
        List<Size> pList = new ArrayList<>();
        String sql = "select * from Sizes";


        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int size_id = rs.getInt("size_id");
                String size_name = rs.getString("size_name");
                String description = rs.getString("description");
                Size s = new Size(size_id, size_name, description);
                pList.add(s);     
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
     
     public Size getSizeById(int id){
         String sql = "select * from Sizes where [size_id] = ?";
         
         try {
             PreparedStatement ps = connection.prepareStatement(sql);
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 int size_id = rs.getInt(1);
                String size_name = rs.getString(2);
                String description = rs.getString(3);
                return new Size(size_id, size_name, description);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(SizeDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return null;
     }
}
