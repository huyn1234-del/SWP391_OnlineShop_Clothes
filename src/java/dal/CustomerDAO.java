/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author Admin
 */
public class CustomerDAO extends DBContext {

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT [user_id]    \n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[gender]\n"
                + "      ,[email]\n"
                + "      ,[phone]\n"
                + "      ,[is_active]      \n"
                + "  FROM [OnlineShop_Clothes].[dbo].[Users]\n"
                + "  "; // Truy vấn lấy tất cả danh mục

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet đúng cột của bảng
                Customer customer = new Customer(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("is_active")
                );
                list.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Customer InfoCustomer(int id) {
        String sql = "SELECT [user_id],[first_name], [last_name], [phone], [email], [gender], [dob], [is_active] "
                + "FROM [OnlineShop_Clothes].[dbo].[Users] "
                + "WHERE [user_id] = ?";
        Customer customer = null;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("dob"),
                        rs.getBoolean("is_active")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sql = "SELECT first_name, last_name, phone, email, gender, dob, is_active FROM [dbo].[Users] WHERE user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                boolean gender = rs.getBoolean("gender");
                String dob = rs.getString("dob");
                boolean isActive = rs.getBoolean("is_active");

                customer = new Customer(id, firstName, lastName, gender, email, phone, dob, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updateCustomer(Customer c) {
        String sql = "UPDATE [dbo].[Users] SET "
                + " [first_name] = ?,"
                + " [last_name] = ?,"
                + " [phone] = ?,"
                + " [email] = ?,"
                + " [gender] = ?,"
                + " [dob] = ?,"
                + " [is_active] = ?"
                + " WHERE user_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getFirst_name());
            st.setString(2, c.getLast_name());
            st.setString(3, c.getPhone());
            st.setString(4, c.getEmail());
            st.setBoolean(5, c.isGender());
            st.setString(6, c.getDob());
            st.setBoolean(7, c.is_active);
            st.setInt(8, c.getUser_id());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> searchCustomerByLastName(String lastName) {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT [user_id], [first_name], [last_name], [gender], [email], [phone], [is_active] "
                + "FROM [OnlineShop_Clothes].[dbo].[Users] "
                + "WHERE [last_name] LIKE ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + lastName + "%"); // Tìm kiếm theo ký tự gần đúng
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("is_active")
                );
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        CustomerDAO customer = new CustomerDAO();
        List<Customer> list = customer.getAllCustomer();
        System.out.println(list.get(0).user_id);

    }
}
