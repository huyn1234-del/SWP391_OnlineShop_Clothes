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
 * @author quanpyke
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
        
       
        
}
    