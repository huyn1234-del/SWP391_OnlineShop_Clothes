/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;
import model.PostCategory;
import model.Product;
import model.Role;
import model.SaleChart;
import model.User;

/**
 *
 * @author Dell
 */
public class PostDAO extends DBContext{
    public List<Post> getAllPost () {
        List<Post> pList = new ArrayList<>();
       String sql = "select * from Posts where is_active = 1;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    public List<Post> getAllPostMarketing () {
        List<Post> pList = new ArrayList<>();
       String sql = "select p.*, (u.first_name+' '+u.last_name) as author_name  \n" +
                    "from Posts as p, Users as u \n" +
                    "where p.author_id = u.user_id;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                String author_name = rs.getString("author_name");
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id,author_name);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    public List<Post> getAllPostMarketingByAuthorId (String id) {
        List<Post> pList = new ArrayList<>();
       String sql = "select p.*, (u.first_name+' '+u.last_name) as author_name  \n" +
                    "from Posts as p, Users as u \n" +
                    "where p.author_id = u.user_id and p.author_id=?;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                String author_name = rs.getString("author_name");
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id,author_name);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    public List<Post> getAllPostByCategoryId(String id) {
        List<Post> pList = new ArrayList<>();
       String sql = "select * from Posts \n" +
                    "where post_category_id=?\n" +
                    "and is_active = 1";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    public List<Post> get3PostByCategoryId(String pcidString, String pid) {
        List<Post> pList = new ArrayList<>();
       String sql = "select top 3 *\n" +
                    "from Posts\n" +
                    "where post_category_id=?\n" +
                    "and post_id not in(?) \n" +
                    "and is_active = 1";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, pcidString);
            pre.setString(2, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    public void AddNewPost(Post p) {
       
       String sql = "INSERT INTO Posts (title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?, 1, GETDATE(), GETDATE(), ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, p.getTitle());
            pre.setString(2, p.getContent());
            pre.setString(3, p.getThumbnail());
            pre.setInt(4, p.getAuthor_id());
            pre.setInt(5, p.getPost_category_id());
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    public void EditPost(Post p) {
       
       String sql = "update Posts\n" +
                    "set \n" +
                    "title =?,\n" +
                    "content =?,\n" +
                    "thumbnail=?,\n" +
                    "is_active = ?,\n" +
                    "modified_at = GETDATE(),\n" +
                    "post_category_id = ?\n" +
                    "where post_id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, p.getTitle());
            pre.setString(2, p.getContent());
            pre.setString(3, p.getThumbnail());
            pre.setInt(4, p.getIs_active());
            pre.setInt(5, p.getPost_category_id());
            pre.setInt(6, p.getPost_id());
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
     public void ShowHidePost(String id, String is_active) {
       
       String sql = "update Posts\n" +
                    "set \n" +
                    "is_active = ?\n" +
                    "where post_id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, is_active);
            pre.setString(2, id);
            
            pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public Post getPostAfter(String auid, String pid) {
        Post post = null;
       String sql = "select top 1 *\n" +
                    "from Posts\n" +
                    "where author_id=?\n" +
                    "and post_id not in(?) \n" +
                    "and is_active = 1\n" +
                    "and modified_at >= (\n" +
                    "select modified_at\n" +
                    "from Posts where post_id = ?\n" +
                    ")\n" +
                    "order by modified_at asc";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, auid);
            pre.setString(2, pid);
            pre.setString(3, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }
    
    
    public Post getPostBefore(String auid, String pid) {
        Post post = null;
       String sql = "select top 1 *\n" +
                    "from Posts\n" +
                    "where author_id=?\n" +
                    "and post_id not in(?) \n" +
                    "and is_active = 1\n" +
                    "and modified_at <= (\n" +
                    "select modified_at\n" +
                    "from Posts where post_id = ?\n" +
                    ")\n" +
                    "order by modified_at desc";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, auid);
            pre.setString(2, pid);
            pre.setString(3, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }
    
    public List<Post> getTop6Post (int n) {
        List<Post> pList = new ArrayList<>();
       String sql = "select * from Posts\n" +
                    "where is_active = 1\n" +
                    "order by post_id\n" +
                    "offset "+n+" rows fetch next 6 rows only";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    public List<Post> getAllPostByFilter(String sql) {
        List<Post> pList = new ArrayList<>();
       
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    public List<Post> getAllPostByFilterMkt(String sql) {
        List<Post> pList = new ArrayList<>();
       
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                 String author_name = rs.getString("authorname");
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id,author_name);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    public Post getPostByID(String id) {
        Post p = new Post();
       String sql = "select * from Posts\n" +
                    "where post_id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                p = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public PostCategory getPostCategoryByPostID(String id) {
       PostCategory p = new PostCategory();
       String sql = "select * from Post_Categories\n" +
                    "where post_category_id = (\n" +
                    "select post_category_id\n" +
                    "from Posts where post_id = ?\n" +
                    ") and is_active = 1";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_category_id = rs.getInt("post_category_id");
                String post_category_name = rs.getString("post_category_name");
                int is_active = rs.getInt("is_active");
                p = new PostCategory(post_category_id, post_category_name, is_active);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    
    public User getUserByPostID(String id) {
        RoleDAO roleDAO = new RoleDAO();
        User u = new User();
       String sql = "select * from Users\n" +
                    "where user_id = (\n" +
                    "select author_id\n" +
                    "from Posts where post_id = ?\n" +
                    ") and is_active=1";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                boolean gender = rs.getBoolean("gender");
                String dob = rs.getString("dob");
                String verification_code = rs.getString("verification_code");
                String reset_password_code = rs.getString("reset_password_code");
                String google_id = rs.getString("google_id");
                String profile_picture_url = rs.getString("profile_picture_url");
                boolean is_active = rs.getBoolean("is_active");
                boolean is_banned = rs.getBoolean("is_banned");
                Role role = roleDAO.getRoleById(rs.getInt(16));
                u = new User(user_id, username, password, first_name, last_name, phone, email, gender, dob, verification_code, reset_password_code, google_id, profile_picture_url, is_active, is_banned, role);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
   
    public List<Post> getTop6ByFilter(String sql) {
        List<Post> pList = new ArrayList<>();
       
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    public List<Post> getNewPost () {
        List<Post> pList = new ArrayList<>();
       String sql = "select *, DATEDIFF(DAY, modified_at ,GETDATE()) from Posts\n" +
                    "where is_active=1\n" +
                    "order by DATEDIFF(DAY, modified_at, GETDATE())";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String thumbnail = rs.getString("thumbnail");
                int author_id = rs.getInt("author_id");
                int is_active = rs.getInt("is_active");
                Date created_at = rs.getDate("created_at");
               Date modified_at = rs.getDate("modified_at");
                int post_category_id = rs.getInt("post_category_id");
                
                Post post = new Post(post_id, title, content, thumbnail, author_id, is_active, created_at, modified_at, post_category_id);
                pList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
    
    
    
    public List<SaleChart> getNumberPostByDay(LocalDate startDate, LocalDate endDate) {
        List<SaleChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select count(post_id) as Total_number from Posts\n"
                + "where created_at <= ?";

          int daybetween= (int)ChronoUnit.DAYS.between(startDate, endDate);


        for (int i = 0; i <= daybetween; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
                pre.setString(1, date + "");

                ResultSet rs = pre.executeQuery();
                while (rs.next()) {

                    String fdate = dtf.format(date);
                    int value = rs.getInt("Total_number");
                    SaleChart saleChart = new SaleChart(fdate, value);
                    sList.add(saleChart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return sList;
    }
    
    
     public List<SaleChart> getPostEachDay(LocalDate startDate, LocalDate endDate) {
        List<SaleChart> sList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "select count(post_id) as Total_number from Posts\n"
                + "where YEAR(created_at)=? AND MONTH(created_at)=? AND DAY(created_at)=?  ";

          int daybetween= (int)ChronoUnit.DAYS.between(startDate, endDate);


        for (int i = 0; i <= daybetween; i++) {

            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                LocalDate date = startDate.plusDays(i);
                pre.setString(1, date.getYear()+  "");
         pre.setString(2, date.getMonthValue()+  "");
             pre.setString(3, date.getDayOfMonth() +  "");
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {

                    String fdate = dtf.format(date);
                    int value = rs.getInt("Total_number");
                    SaleChart saleChart = new SaleChart(fdate, value);
                    sList.add(saleChart);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return sList;
    }
    
    
    
    public static void main(String[] args) {
        PostDAO pdao = new PostDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
              String pobegin = "2024-09-21";
                      LocalDate poDate = LocalDate.parse(pobegin, formatter);

        List<SaleChart> list=pdao.getPostEachDay(poDate, LocalDate.now());
        System.out.println(list.get(0).getValue());
    
    }
}
