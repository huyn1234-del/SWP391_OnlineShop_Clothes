/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Sliders;

/**
 *
 * @author ADMIN
 */
public class SlidersDAO extends DBContext {

    public ArrayList<Sliders> getAllSliders() {
        ArrayList<Sliders> list = new ArrayList<>();
        String sql = "select * from Sliders";
        try {
            //thuc thi cau truy van
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("slider_id");
                String title=rs.getString("tittle");
                String description = rs.getString("description");
                String img = rs.getString("image_url");
                int status = rs.getInt("is_active");
                Sliders s=new Sliders(id, title, description, img, status);
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("e");
        }

        return list;
    }

    public ArrayList<Sliders> getSliderPaging(int index) {
        ArrayList<Sliders> list = new ArrayList<>();
        String sql = "Select * from Sliders\n"
                + " order by slider_id\n"
                + " offset ? rows\n"
                + " fetch first 2 rows only";
        try {
            //thuc thi cau truy van
            PreparedStatement pre = connection.prepareStatement(sql);
                
            pre.setInt(1, (index-1)*2);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("slider_id");
                String description = rs.getString("description");
                 String title=rs.getString("tittle");
                String img = rs.getString("image_url");
                int status = rs.getInt("is_active");
               Sliders s=new Sliders(id, title, description, img, status);
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("e");
        }

        return list;
    }
    
     public void insert(Sliders s) {
        try {
            String sql = "insert into Sliders (description,image_url,is_active,tittle) \n"
                    + "values\n"
                    + "(?, ?, ?, ? )"
                    + "  DBCC CHECKIDENT (YourTableName, RESEED, 4);";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getDescription());
            pre.setString(2, s.getImg());
            pre.setInt(3, s.getStatus());
            pre.setString(4, s.getTitle());
           pre.executeUpdate();

        } catch (SQLException ex) {

        }
    }
    

     
     public void update(Sliders s) {
        String sql = "UPDATE Sliders SET description=?, image_url=?, is_active=?, tittle=? WHERE slider_id=? ";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            
            pre.setString(1, s.getDescription());
            pre.setString(2, s.getImg());
          
            pre.setInt(3, s.getStatus());
            pre.setString(4, s.getTitle());
            pre.setInt(5, s.getId());
           
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     public Sliders getSliderById(int id)
     {
         Sliders s=null;
         ArrayList<Sliders> slist=getAllSliders();
         for(int i=0;i<slist.size();i++)
         {
             if(slist.get(i).getId()==id)
             {
                 s=slist.get(i);
                 break;
             }
         }
         return s;
         
     }
      public void delete(int id) {
        String sql = "Delete from Sliders WHERE slider_id=? ";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            
            pre.setInt(1, id);
           
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
  
}
        public ArrayList<Sliders> searchSlider(String search) {
        ArrayList<Sliders> list = new ArrayList<>();
        String sql = "select * from Sliders\n "
                + "where tittle like ? OR description like ? ";
        try {
            //thuc thi cau truy van
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%"+search+"%");
            pre.setString(2, "%"+search+"%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("slider_id");
                String title=rs.getString("tittle");
                String description = rs.getString("description");
                String img = rs.getString("image_url");
                int status = rs.getInt("is_active");
                Sliders s=new Sliders(id, title, description, img, status);
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("e");
        }

        return list;
    }
        
       
        
}
    