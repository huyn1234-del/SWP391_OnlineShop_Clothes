
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;
import model.User;


/**
 *
 * @author ADMIN
 */

public class UserDAO extends DBContext {
  
    public boolean updateVerifyCode(int user_id, String verifyCode) {
        String sql = """
                     UPDATE [dbo].[Users]
                        SET [verification_code] = ?
                      WHERE [user_id] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, verifyCode);
            ps.setInt(2, user_id);

            int exe = ps.executeUpdate();
            if (exe > 0) {
                return true;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteVerifyCode(int userId) {
        String sql = """
                     UPDATE [dbo].[Users]
                        SET [verification_code] = null
                      WHERE [user_id] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            int exe = ps.executeUpdate();
            if (exe > 0) {
                return true;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

public User login(String email, String password) {      
    String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
    try {         
        PreparedStatement ps = connection.prepareStatement(sql);         
        ps.setString(1, email);         
        ps.setString(2, password);          
        ResultSet rs = ps.executeQuery();         
        if (rs.next()) {             
            return getUserByEmail(email);  // Cần thay đổi hàm này nếu chưa có
        }     
    } catch (SQLException ex) {         
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);     
    }     
    return null; 
}


    public boolean activateUser(String username) {
        boolean check = false;
        String sql = """
                     UPDATE [dbo].[Users]
                        SET [is_active] = ?
                      WHERE [username] = ?""";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setString(2, username);

            int exe = ps.executeUpdate();
            if (exe > 0) {
                check = true;
            }
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return check;
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
                                ,[verification_code]
                                ,[reset_password_code]
                                ,[google_id]
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
            ps.setString(9, user.getVerification_code());
            ps.setString(10, user.getReset_password_code());
            ps.setString(11, user.getGoogle_id());
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
    

    public User getUserById(int id) {
        RoleDAO roleDAO = new RoleDAO();
        ResultSet rs = getData("select * from Users where user_id = " + id);
        User user = null;
        try {
            if (rs.next()) {
                int user_id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String first_name = rs.getString(4);
                String last_name = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                boolean gender = rs.getBoolean(8);
                String dob = rs.getString(9);
                String verification_code = rs.getString(10);
                String reset_password_code = rs.getString(11);
                String google_id = rs.getString(12);
                String profile_picture_url = rs.getString(13);
                boolean is_active = rs.getBoolean(14);
                boolean is_banned = rs.getBoolean(15);
                Role role = roleDAO.getRoleById(rs.getInt(16));
                user = new User(user_id, username, password, first_name, last_name, phone, email, gender, dob, verification_code, reset_password_code, google_id, profile_picture_url, is_active, is_banned, role);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }
    
    
   public User getUserByEmail(String uemail) {     
    RoleDAO roleDAO = new RoleDAO();     
    User user = null;     
    String sql = "SELECT * FROM Users WHERE email = ?";     

    try {         
        PreparedStatement ps = connection.prepareStatement(sql);         
        ps.setString(1, uemail);         
        ResultSet rs = ps.executeQuery();          

        if (rs.next()) {             
            int user_id = rs.getInt(1);             
            String username = rs.getString(2);             
            String password = rs.getString(3);             
            String first_name = rs.getString(4);             
            String last_name = rs.getString(5);             
            String phone = rs.getString(6);             
            String email = rs.getString(7);             
            boolean gender = rs.getBoolean(8);             
            String dob = rs.getString(9);             
            String verification_code = rs.getString(10);             
            String reset_password_code = rs.getString(11);             
            String google_id = rs.getString(12);             
            String profile_picture_url = rs.getString(13);             
            boolean is_active = rs.getBoolean(14);             
            boolean is_banned = rs.getBoolean(15);             
            Role role = roleDAO.getRoleById(rs.getInt(16));              

            user = new User(user_id, username, password, first_name, last_name, phone, email, gender, dob, verification_code, reset_password_code, google_id, profile_picture_url, is_active, is_banned, role);         
        }         
        rs.close();     
    } catch (SQLException ex) {         
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);     
    }     
    return user;  
}


    public User getUserByUsername(String uname) {
        RoleDAO roleDAO = new RoleDAO();
        ResultSet rs = getData("select * from Users where username = '" + uname + "'");
        User user = null;
        try {
            if (rs.next()) {
                int user_id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String first_name = rs.getString(4);
                String last_name = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                boolean gender = rs.getBoolean(8);
                String dob = rs.getString(9);
                String verification_code = rs.getString(10);
                String reset_password_code = rs.getString(11);
                String google_id = rs.getString(12);
                String profile_picture_url = rs.getString(13);
                boolean is_active = rs.getBoolean(14);
                boolean is_banned = rs.getBoolean(15);
                Role role = roleDAO.getRoleById(rs.getInt(16));
                user = new User(user_id, username, password, first_name, last_name, phone, email, gender, dob, verification_code, reset_password_code, google_id, profile_picture_url, is_active, is_banned, role);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }
    
    
    public User getUserByUserId(int rid) {
        RoleDAO roleDAO = new RoleDAO();
        String sql = "select * from Users where user_id = ?";
        User user = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, rid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String first_name = rs.getString(4);
                String last_name = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                boolean gender = rs.getBoolean(8);
                String dob = rs.getString(9);
                String verification_code = rs.getString(10);
                String reset_password_code = rs.getString(11);
                String google_id = rs.getString(12);
                String profile_picture_url = rs.getString(13);
                boolean is_active = rs.getBoolean(14);
                boolean is_banned = rs.getBoolean(15);
                Role role = roleDAO.getRoleById(rs.getInt(16));
                user = new User(user_id, username, password, first_name, last_name, phone, email, gender, dob, verification_code, reset_password_code, google_id, profile_picture_url, is_active, is_banned, role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }
    public boolean checkExistPhone(String phone) {

        ResultSet rs = getData("select * from Users where phone = '" + phone + "'");

        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
    
    public List<User> getListUserByRoleId(int rid) {
        RoleDAO roleDAO = new RoleDAO();
        List<User> list= new ArrayList<>();
        String sql = "select * from Users where role_id = ?";
        User user = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, rid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String first_name = rs.getString(4);
                String last_name = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                boolean gender = rs.getBoolean(8);
                String dob = rs.getString(9);
                String verification_code = rs.getString(10);
                String reset_password_code = rs.getString(11);
                String google_id = rs.getString(12);
                String profile_picture_url = rs.getString(13);
                boolean is_active = rs.getBoolean(14);
                boolean is_banned = rs.getBoolean(15);
                Role role = roleDAO.getRoleById(rs.getInt(16));
                user = new User(user_id, username, password, first_name, last_name, phone, email, gender, dob, verification_code, reset_password_code, google_id, profile_picture_url, is_active, is_banned, role);
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public boolean updatePassword(String email, String password) {
        String sql = """
                     UPDATE [dbo].[Users]
                        SET [password] = ?
                      WHERE [email] = ?""";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);

            int exe = ps.executeUpdate();
            if (exe > 0) {
                return true;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
