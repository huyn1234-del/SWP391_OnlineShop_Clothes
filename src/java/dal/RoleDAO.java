
package dal;

import model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ADMIN
 */
public class RoleDAO extends DBContext {

    public Role getRoleById(int id) {

        ResultSet rs = getData("select * from Roles where role_id = " + id);

        Role role = null;
        try {
            if (rs.next()) {
                int role_id = rs.getInt(1);
                String rolename = rs.getString(2);
                role = new Role(role_id, rolename);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }
    public Role getRolebyname(String rolename){
        Role role= null;
        String sql="select * from Roles where rolename=?";
        try {
            PreparedStatement pre= connection.prepareStatement(sql);
            pre.setString(1, rolename);
            ResultSet rs=pre.executeQuery();
            while (rs.next()) {
                rolename = rs.getString("rolename");
                
                int role_id= rs.getInt("role_id"); 
                role= new Role(role_id, rolename);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return role;
    }
    public List<Role> getAllRole(){
        List<Role> rList= new ArrayList<>();
        String sql="select * from Roles";
        try {
            PreparedStatement pre= connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while (rs.next()) {
                String rolename = rs.getString("rolename");
                
                int role_id= rs.getInt("role_id"); 
                rList.add(new Role(role_id, rolename));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rList;
    }
    

}
