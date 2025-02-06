package dal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        
        try {
            String user = "sa";
            String pass = "123456789";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=OnlineShop";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() {
        return connection;
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        Statement statement;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return rs;
    }
}
