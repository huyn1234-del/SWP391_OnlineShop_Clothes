/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.Slider;

/**
 *
 * @author Admin
 */
public class SliderDAO extends DBContext {

    public List<Slider> getAllSlider() {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * FROM Sliders"; // Truy vấn lấy tất cả danh mục

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet đúng cột của bảng
                Slider slider = new Slider(
                        rs.getInt("slider_id"),
                        rs.getString("tittle"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("is_active")
                );
                list.add(slider);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    //add
    public void AddSlider(String tittle, String description, String image, String active) {
        String sql = "INSERT INTO [dbo].[Sliders]\n"
                + "           ([tittle]\n"
                + "           ,[description]\n"
                + "           ,[image_url]\n"
                + "           ,[is_active])\n"
                + "     VALUES(?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tittle);
            st.setString(2, description);
            st.setString(3, image);
            st.setString(4, active);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //search khi co tittle tra ve 1 list
    public List<Slider> getSlider(String tittle) {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * FROM Sliders WHERE tittle LIKE ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + tittle + "%"); // Thêm dấu % vào giá trị truyền vào
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Slider slider = new Slider(
                        rs.getInt("slider_id"),
                        rs.getString("tittle"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("is_active")
                );
                list.add(slider);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    //search chinh xac 1 tittle duy nhat
    public Slider getSliderbyTittle(String tittle) {
        String sql = "SELECT * FROM Sliders WHERE tittle LIKE ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + tittle + "%"); // Thêm dấu % để tìm kiếm chứa từ khóa
            ResultSet rs = st.executeQuery();

            if (rs.next()) { // Chỉ lấy một dòng kết quả
                return new Slider(
                        rs.getInt("slider_id"),
                        rs.getString("tittle"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("is_active")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn: " + e.getMessage());
        }

        return null; // Trả về null nếu không tìm thấy kết quả
    }

    public Slider getSliderbyid(int id) {
        String sql = "SELECT * FROM Sliders WHERE slider_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) { // Chỉ lấy một dòng kết quả
                return new Slider(
                        rs.getInt("slider_id"),
                        rs.getString("tittle"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("is_active")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn: " + e.getMessage());
        }

        return null; // Trả về null nếu không tìm thấy kết quả
    }

    public void deleteSlider(int id) {
        String sql = "DELETE FROM [dbo].[Sliders]\n"
                + "      WHERE slider_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateSlider(Slider s) {
        String sql = "UPDATE [dbo].[Sliders]\n"
                + "   SET [tittle] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[image_url] = ?\n"
                + "      ,[is_active] = ?\n"
                + " WHERE slider_id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getTittle());
            st.setString(2, s.getDescription());
            st.setString(3, s.getImage_url());
            st.setString(4, s.getIs_active());
            st.setInt(5, s.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void hideSlider(int id) {
        String sql = "UPDATE [dbo].[Sliders]\n"
                + " set    [is_active] = 'False'\n"
                + " WHERE slider_id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void showSlider(int id) {
        String sql = "UPDATE [dbo].[Sliders]\n"
                + "    set  [is_active] = 'True'\n"
                + " WHERE slider_id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Slider InfoSlider(int id) {
        String sql = "SELECT TOP (1000) [slider_id]\n"
                + "      ,[tittle]\n"
                + "      ,[description]\n"
                + "      ,[image_url]\n"
                + "      ,[is_active]\n"
                + "  FROM [OnlineShop_Clothes].[dbo].[Sliders]\n"
                + "  where slider_id = ?";
        Slider slider = null;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Slider(
                        rs.getInt("slider_id"),
                        rs.getString("tittle"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("is_active"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        SliderDAO slider = new SliderDAO();
        List<Slider> list = slider.getAllSlider();
        System.out.println(list.get(1).getId());

    }
}
