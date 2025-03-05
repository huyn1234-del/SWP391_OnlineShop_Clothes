package dal;

import model.PaymentStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentStatusDAO extends DBContext {

    public PaymentStatus getPaymentStatusById(int id) {
        PaymentStatus ps = new PaymentStatus();
        String sql = "select * from Payment_Status where payment_status_id=" + id;

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("payment_status_id");
                String name = rs.getString("payment_status_name");
                String description = rs.getString("description");
                ps = new PaymentStatus(pid, name, description);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    public static void main(String[] args) {
        PaymentStatusDAO dao = new PaymentStatusDAO();
        PaymentStatus p = dao.getPaymentStatusById(1);
        System.out.println(p.getPaymentStatusName());
    }
}
