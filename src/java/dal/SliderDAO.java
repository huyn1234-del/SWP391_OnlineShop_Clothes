
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Slider;
import model.Sliders;


public class SliderDAO extends DBContext {
    
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
        
        
        
        //cua thang 
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
    

