/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {
   
      public boolean checkExistUsername(String username) {

        ResultSet rs = getData("select * from Users where username = '" + username + "'");

        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean checkExistEmail(String email) {

        ResultSet rs = getData("select * from Users where email = '" + email + "'");

        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
    public Integer insertUser(User user) {
        Integer userId = null;
        String sql = """
                     INSERT INTO [dbo].[Users]
                                ([username]
                                ,[password]
                                ,[first_name]
                                ,[last_name]
                                ,[phone]
                                ,[email]
                                ,[gender]
                                ,[dob]
                                ,[reset_password_code]
                                ,[profile_picture_url]
                                ,[is_active]
                                ,[is_banned]
                                ,[role_id])
                          VALUES
                                (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirst_name());
            ps.setString(4, user.getLast_name());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getEmail());
            ps.setBoolean(7, user.isGender());
            ps.setString(8, user.getDob());
            ps.setString(10, user.getReset_password_code());
            ps.setString(12, user.getProfile_picture_url());
            ps.setBoolean(13, user.isIs_active());
            ps.setBoolean(14, user.isIs_banned());
            ps.setInt(15, user.getRole().getRole_id());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userId;
    }
}
