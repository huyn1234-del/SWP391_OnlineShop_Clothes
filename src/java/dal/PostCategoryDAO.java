/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;
import model.PostCategory;

/**
 *
 * @author Dell
 */
public class PostCategoryDAO extends DBContext{
     public List<PostCategory> getAllPostCategory() {
        List<PostCategory> pList = new ArrayList<>();
       String sql = "select * from Post_Categories where is_active = 1;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_category_id = rs.getInt("post_category_id");
                String post_category_name = rs.getString("post_category_name");
                int is_active = rs.getInt("is_active");
                
                PostCategory pc = new PostCategory(post_category_id, post_category_name, is_active);
                pList.add(pc);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
     
     
     
      public PostCategory getPostCategories(String id){
       PostCategory pc=null;
       String sql=" select * from Post_Categories\n" +
                " where post_category_id=?";
       try{
           PreparedStatement pre= connection.prepareStatement(sql);
           pre.setString(1, id);
           ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_category_id = rs.getInt("post_category_id");
                String post_category_name = rs.getString("post_category_name");
                int is_active = rs.getInt("is_active");
                pc =new PostCategory(post_category_id, post_category_name, is_active);
                
            }
            
       }catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return pc;
   }
}
